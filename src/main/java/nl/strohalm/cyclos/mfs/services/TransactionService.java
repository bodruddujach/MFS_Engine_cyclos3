package nl.strohalm.cyclos.mfs.services;


import nl.strohalm.cyclos.entities.accounts.AccountType;
import nl.strohalm.cyclos.entities.accounts.MemberAccount;
import nl.strohalm.cyclos.entities.accounts.fees.transaction.SimpleTransactionFee;
import nl.strohalm.cyclos.entities.accounts.fees.transaction.TransactionFee;
import nl.strohalm.cyclos.entities.accounts.fees.transaction.TransactionFeeQuery;
import nl.strohalm.cyclos.entities.accounts.transactions.Payment;
import nl.strohalm.cyclos.entities.accounts.transactions.Transfer;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType.TxnTypeTag;
import nl.strohalm.cyclos.mfs.exceptions.ErrorConstants;
import nl.strohalm.cyclos.mfs.exceptions.MFSCommonException;
import nl.strohalm.cyclos.mfs.middleware.CyclosMiddleware;
import nl.strohalm.cyclos.mfs.models.accounts.BalanceResponse;
import nl.strohalm.cyclos.mfs.models.accounts.CheckPinRequest;
import nl.strohalm.cyclos.mfs.models.accounts.WalletStatementDetail;
import nl.strohalm.cyclos.mfs.models.accounts.WalletStatementResp;
import nl.strohalm.cyclos.mfs.models.enums.TransactionType;
import nl.strohalm.cyclos.mfs.models.transactions.BulkTxnRequest;
import nl.strohalm.cyclos.mfs.models.transactions.TxnPair;
import nl.strohalm.cyclos.mfs.models.transactions.TxnRequest;
import nl.strohalm.cyclos.mfs.models.transactions.TxnResponse;
import nl.strohalm.cyclos.mfs.models.transactions.TxnReversalRequest;
import nl.strohalm.cyclos.mfs.models.transactions.TxnReversalResponse;
import nl.strohalm.cyclos.mfs.utils.MfsConstant;
import nl.strohalm.cyclos.services.transactions.BulkPaymentResult;
import nl.strohalm.cyclos.services.transactions.DoPaymentDTO;
import nl.strohalm.cyclos.services.transactions.PaymentServiceLocal;
import nl.strohalm.cyclos.services.transfertypes.TransactionFeePreviewDTO;
import nl.strohalm.cyclos.services.transfertypes.TransactionFeeServiceLocal;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static nl.strohalm.cyclos.mfs.exceptions.ErrorConstants.*;

@Service
public class TransactionService {

  @Autowired
  PaymentServiceLocal paymentServiceLocal;
  @Autowired
  CyclosMiddleware cyclosMiddleware;
  @Autowired
  MfsAccountService accountService;
  @Autowired
  private TransactionFeeServiceLocal transactionFeeService;

  @Autowired
  private MfsTxnTypeService mfsTxnTypeService;
  //todo autowire limit service

  public TxnResponse processTransaction(TxnRequest request) {
    MfsTxnType mfsTxnType = cyclosMiddleware.validateMFSTxnType(request);
    DoPaymentDTO doPaymentDTO = cyclosMiddleware.getValidateCyclosDoPaymentDTO(request, mfsTxnType);

    //login wallet
    walletLogin(request, mfsTxnType);
    // todo check limit validation
    Payment payment = paymentServiceLocal.doPayment(doPaymentDTO);
    // update limit
    TxnResponse txnResponse = cyclosMiddleware.convertTxnResult(payment, request);
    updateFeeInfo(request, txnResponse, mfsTxnType);
    includeBalances(request, txnResponse, mfsTxnType);
    return txnResponse;
  }

  public List<TxnResponse> processBulkTxn(BulkTxnRequest bulkTxnRequest) {
    List<DoPaymentDTO> doPaymentDTOList = new LinkedList<>();
    Map<String, MfsTxnType> mfsBulkTxnTypes = new HashMap<>();
    Map<String, List<TxnRequest>> dynamicFeesAndCommissions = new HashMap<>();
    List<TxnPair> txnPairs = new ArrayList<>();

    for (TxnRequest txnRequest : bulkTxnRequest.getTxnRequestList()) {
      MfsTxnType mfsTxnType = cyclosMiddleware.validateMFSTxnType(txnRequest);
      mfsBulkTxnTypes.put(mfsTxnType.getName(), mfsTxnType);
      walletLogin(txnRequest, mfsTxnType);
      DoPaymentDTO doPaymentDTO = cyclosMiddleware.getValidateCyclosDoPaymentDTO(txnRequest, mfsTxnType);
      doPaymentDTO.setMfsTransactionType(txnRequest.getTxnType());
      if (StringUtils.isBlank(txnRequest.getRequestId())) {
        throw new MFSCommonException(ErrorConstants.INVALID_TXN_REQUEST, "Transaction request id is not found for Bulk Payment", HttpStatus.BAD_REQUEST);
      }
      if (StringUtils.isNotBlank(txnRequest.getParentRequestId()) && mfsTxnType.getTypeCategory().equals(MfsTxnType.TypeCategory.MAIN)) {
        throw new MFSCommonException(ErrorConstants.INVALID_TXN_REQUEST, ErrorConstants.ERROR_MAP.get(ErrorConstants.INVALID_TXN_REQUEST), HttpStatus.BAD_REQUEST);
      }
      if (mfsTxnType.getTypeCategory().equals(MfsTxnType.TypeCategory.FEE) || mfsTxnType.getTypeCategory().equals(MfsTxnType.TypeCategory.COMMISSION)) {
        if (StringUtils.isBlank(txnRequest.getParentRequestId())) {
          throw new MFSCommonException(ErrorConstants.INVALID_FEE_OR_COMMISSION, ErrorConstants.ERROR_MAP.get(ErrorConstants.INVALID_FEE_OR_COMMISSION), HttpStatus.BAD_REQUEST);
        }
        List<TxnRequest> mainTxnFeesAndCommissions = dynamicFeesAndCommissions.get(txnRequest.getParentRequestId());
        if (CollectionUtils.isEmpty(mainTxnFeesAndCommissions)) {
          mainTxnFeesAndCommissions = new ArrayList<>();
        }
        mainTxnFeesAndCommissions.add(txnRequest);
        dynamicFeesAndCommissions.put(txnRequest.getParentRequestId(), mainTxnFeesAndCommissions);
      }
      doPaymentDTOList.add(doPaymentDTO);
    }
    List<BulkPaymentResult> bulkPaymentResultList = paymentServiceLocal.doBulkPayment(doPaymentDTOList);

    if (!CollectionUtils.isEmpty(bulkTxnRequest.getTxnRequestList()) && !CollectionUtils.isEmpty(bulkPaymentResultList)) {
        for (int i=0; i<bulkPaymentResultList.size(); i++) {
        TxnPair txnPair = new TxnPair();
        txnPair.setRequest(bulkTxnRequest.getTxnRequestList().get(i));
        txnPair.setPaymentResult(bulkPaymentResultList.get(i));
        txnPairs.add(txnPair);
      }
    }
    return processBulkPaymentResponse(txnPairs, mfsBulkTxnTypes, dynamicFeesAndCommissions);
  }

  public TxnResponse processDynamicTxn(BulkTxnRequest bulkTxnRequest) {
    List<DoPaymentDTO> doPaymentDTOList = new LinkedList<>();
    List<TxnRequest> filteredDynamicTxnRequests = new ArrayList<>();
    Map<String, MfsTxnType> mfsBulkTxnTypes = new HashMap<>();
    Map<String, List<TxnRequest>> dynamicFeesAndCommissions = new HashMap<>();
    List<TxnPair> txnPairs = new ArrayList<>();
    int countMainTxn = 0;
    TxnRequest firstMainTxnRequest = null;
    for (TxnRequest txnRequest : bulkTxnRequest.getTxnRequestList()) {
      MfsTxnType mfsTxnType = cyclosMiddleware.validateMFSTxnType(txnRequest);
      mfsBulkTxnTypes.put(mfsTxnType.getName(), mfsTxnType);
      walletLogin(txnRequest, mfsTxnType);
      DoPaymentDTO doPaymentDTO = cyclosMiddleware.getValidateCyclosDoPaymentDTO(txnRequest, mfsTxnType);
      doPaymentDTO.setMfsTransactionType(txnRequest.getTxnType());
      if (StringUtils.isBlank(txnRequest.getRequestId())) {
        throw new MFSCommonException(ErrorConstants.INVALID_TXN_REQUEST, "Transaction request id is not found", HttpStatus.BAD_REQUEST);
      }
      if (StringUtils.isNotBlank(txnRequest.getParentRequestId()) && mfsTxnType.getTypeCategory().equals(MfsTxnType.TypeCategory.MAIN)) {
        throw new MFSCommonException(ErrorConstants.INVALID_TXN_REQUEST, ErrorConstants.ERROR_MAP.get(ErrorConstants.INVALID_TXN_REQUEST), HttpStatus.BAD_REQUEST);
      }
      if (mfsTxnType.getTypeCategory().equals(MfsTxnType.TypeCategory.MAIN)) {
        if (countMainTxn > 1) {
          throw new MFSCommonException(ErrorConstants.INVALID_TXN_REQUEST, "More than one transaction is not allowed", HttpStatus.BAD_REQUEST);
        } else {
          countMainTxn++;
          firstMainTxnRequest = txnRequest;
        }
      }
      if (mfsTxnType.getTypeCategory().equals(MfsTxnType.TypeCategory.FEE) || mfsTxnType.getTypeCategory().equals(MfsTxnType.TypeCategory.COMMISSION)) {
        if (StringUtils.isBlank(txnRequest.getParentRequestId())) {
          throw new MFSCommonException(ErrorConstants.INVALID_FEE_OR_COMMISSION, ErrorConstants.ERROR_MAP.get(ErrorConstants.INVALID_FEE_OR_COMMISSION), HttpStatus.BAD_REQUEST);
        }
        List<TxnRequest> mainTxnFeesAndCommissions = dynamicFeesAndCommissions.get(txnRequest.getParentRequestId());
        if (CollectionUtils.isEmpty(mainTxnFeesAndCommissions)) {
          mainTxnFeesAndCommissions = new ArrayList<>();
        }
        mainTxnFeesAndCommissions.add(txnRequest);
        dynamicFeesAndCommissions.put(txnRequest.getParentRequestId(), mainTxnFeesAndCommissions);
      }
      if (firstMainTxnRequest!= null && (firstMainTxnRequest.getRequestId().equals(txnRequest.getRequestId()) || firstMainTxnRequest.getRequestId().equals(txnRequest.getParentRequestId()))) {
        filteredDynamicTxnRequests.add(txnRequest);
        doPaymentDTOList.add(doPaymentDTO);
      }
    }
    List<BulkPaymentResult> bulkPaymentResultList = paymentServiceLocal.doBulkPayment(doPaymentDTOList);
    if (!CollectionUtils.isEmpty(filteredDynamicTxnRequests) && !CollectionUtils.isEmpty(bulkPaymentResultList)) {
      for (int i=0; i<bulkPaymentResultList.size(); i++) {
        TxnPair txnPair = new TxnPair();
        txnPair.setRequest(filteredDynamicTxnRequests.get(i));
        txnPair.setPaymentResult(bulkPaymentResultList.get(i));
        txnPairs.add(txnPair);
      }
    }
    return processDynamicPaymentResponse(firstMainTxnRequest, txnPairs, mfsBulkTxnTypes, dynamicFeesAndCommissions);
  }
  
//  public List<TxnResponse> processDynamicTxn(BulkTxnRequest bulkTxnRequest) {
//    List<DoPaymentDTO> doPaymentDTOList = cyclosMiddleware.getBulkPaymentDTOList(bulkTxnRequest);
//    List<TxnPair> txnPairs = new ArrayList<>();
//    Map<String, MfsTxnType> mfsBulkTxnTypes = new HashMap<>();
//    for (TxnRequest request: bulkTxnRequest.getTxnRequestList()) {
//        MfsTxnType mfsTxnType = cyclosMiddleware.validateMFSTxnType(request);
//        mfsBulkTxnTypes.put(mfsTxnType.getName(), mfsTxnType);
//        walletLogin(request, mfsTxnType);
//    }
//    List<BulkPaymentResult> bulkPaymentResultList = paymentServiceLocal.doBulkPayment(doPaymentDTOList);
//    if (!CollectionUtils.isEmpty(bulkTxnRequest.getTxnRequestList()) && !CollectionUtils.isEmpty(bulkPaymentResultList)) {
//      for (int i=0; i<bulkPaymentResultList.size(); i++) {
//        TxnPair txnPair = new TxnPair();
//        txnPair.setRequest(bulkTxnRequest.getTxnRequestList().get(i));
//        txnPair.setPaymentResult(bulkPaymentResultList.get(i));
//        txnPairs.add(txnPair);
//      }
//    }
//    return processDynamicPaymentResponse(txnPairs, bulkTxnRequest.getTxnRequestList());
//  }

  public WalletStatementResp getTxnDetail(String txnId) {
	WalletStatementResp statementResponse = paymentServiceLocal.getTransactionDetails(txnId);
	if (!CollectionUtils.isEmpty(statementResponse.getWalletStatementDetailList())) {
		for (WalletStatementDetail detail: statementResponse.getWalletStatementDetailList()) {
			MfsTxnType mfsTxnType = mfsTxnTypeService.findByCoreId(detail.getTypeId().longValue());
            detail.setMfsTxnType(mfsTxnType != null ? mfsTxnType.getName() : "");
		}
	}
	return statementResponse;
  }

  public TxnResponse estimate(TxnRequest request) {
    final MfsTxnType txnType = cyclosMiddleware.validateMFSTxnType(request);
    final TransactionFeeQuery query = new TransactionFeeQuery();
    DoPaymentDTO doPaymentDTO = cyclosMiddleware.getValidateCyclosDoPaymentDTO(request, txnType);

    query.setTransferType(doPaymentDTO.getTransferType());
    final List<? extends TransactionFee> fees = transactionFeeService.search(query);
    BigDecimal feeTotalAmount = BigDecimal.ZERO;
    BigDecimal commissionTotalAmount = BigDecimal.ZERO;
    for (final TransactionFee fee : fees) { 
      if (fee.isEnabled() && fee.getGeneratedTransferType().getTo().getNature() == AccountType.Nature.MEMBER && fee.getGeneratedTransferType().getTo().getName().toLowerCase().contains(MfsConstant.AGENT.toLowerCase())) {
        if (fee instanceof SimpleTransactionFee && fee.getAmount().isPercentage()) {
          final BigDecimal feeValue = fee.getAmount().getValue();
          commissionTotalAmount = commissionTotalAmount.add(request.getAmount().multiply(feeValue).divide(BigDecimal.valueOf(100)));
        } else {
          commissionTotalAmount = commissionTotalAmount.add(fee.getAmount().getValue());
        }
      }
    }

    TransactionFeePreviewDTO feePreviewDTO = transactionFeeService.preview(doPaymentDTO.getFrom(), doPaymentDTO.getTo(), doPaymentDTO.getTransferType(),doPaymentDTO.getAmount());
    if (!CollectionUtils.isEmpty(feePreviewDTO.getFees())) {
      for (final TransactionFee fee : feePreviewDTO.getFees().keySet()) {
        feeTotalAmount = feeTotalAmount.add(feePreviewDTO.getFees().get(fee));
      }
    }
    TxnResponse response = new TxnResponse();
    response.setFee(feeTotalAmount);
    response.setAmount(request.getAmount());
    response.setCommission(commissionTotalAmount);
    response.setBalanceFrom(null);
    response.setBalanceTo(null);
    response.setTicket(null);
    response.setTraceNo(null);
    return response;
  }

  public TxnReversalResponse processReverseTxn(TxnReversalRequest request) {
    return processReverseTxn(request, true);
  }

  public TxnReversalResponse processReverseTxn(TxnReversalRequest request, boolean lock) {
    Transfer transfer = paymentServiceLocal.findByTxnId(request.getTxnId());
    if(transfer.getChargedBackBy()!=null){
      throw new MFSCommonException(TXN_ALREADY_REVERSE, ERROR_MAP.get(TXN_ALREADY_REVERSE), HttpStatus.BAD_REQUEST);
    }
    validateReversalTxn(request, transfer);
    Transfer chargebackTransfer = paymentServiceLocal.chargeback(transfer);
    TxnReversalResponse txnReversalResponse = new TxnReversalResponse();
    txnReversalResponse.setStatus(MfsConstant.STATUS_SUCCESS);
    txnReversalResponse.setMessage("Reverse Txn Successful");
    txnReversalResponse.setTxnId(chargebackTransfer.getTransactionNumber());
    return txnReversalResponse;
  }

  public Transfer getTransfer(String txnId) {
    return paymentServiceLocal.findByTxnId(txnId);
  }

  public TxnResponse getTxnDetailByCustomerRefId(String customerRefId) {
     Transfer transfer = paymentServiceLocal.loadTransferByCustomerRefId(customerRefId);
     if (transfer == null) {
         throw new MFSCommonException(ErrorConstants.TRANSACTION_NOT_FOUND, String.format("TRANSACTION_DETAIL_NOT_FOUND: CUSTOMER_REF_ID %s", customerRefId), HttpStatus.NOT_FOUND);
     }
     return cyclosMiddleware.convertTxnResult(transfer, null);
  }
  
  private void vaidateRequestedPinInput(TxnRequest request) {
    if ("****".equals(request.getPin()) || StringUtils.isBlank(request.getPin())) {
        throw new MFSCommonException(ErrorConstants.INVALID_PIN, ErrorConstants.ERROR_MAP.get(ErrorConstants.INVALID_PIN), HttpStatus.BAD_REQUEST);
    }
//      && (request.getTxnType() == TransactionType.BANK_CASHOUT
//      || request.getTxnType() == TransactionType.BANK_CASHOUT_MERCHANT
//      || request.getTxnType() == TransactionType.BANK_CASHOUT_CUSTOMER
//      || request.getTxnType() == TransactionType.TOPUP_AGENT
//      || request.getTxnType() == TransactionType.SERVICE_FEE_CASH_OUT
//      || request.getTxnType() == TransactionType.AGENT_COMMISSION_CASH_OUT)
  }

  private void updateFeeInfo(TxnRequest request, TxnResponse txnResponse, MfsTxnType txnType) {
//    if (request.getTxnType() == TransactionType.SEND_MONEY
//      || request.getTxnType() == TransactionType.PAYMENT
//      || request.getTxnType() == TransactionType.CASH_OUT_TO_MFS_AGENT
//      || request.getTxnType() == TransactionType.BILL_PAYMENT
//      || request.getTxnType() == TransactionType.AGENT_ASSISTED_PAYMENT
//      || request.getTxnType() == TransactionType.UTILITY_BILL_PAYMENT_WASA_SSL
//      || request.getTxnType() == TransactionType.BANK_CASHOUT_CUSTOMER) 
    if (txnType.isIncludeFeeInResponse()) {
      TxnResponse feeResponse = estimate(request);
      txnResponse.setFee(feeResponse.getFee());
      txnResponse.setCommission(feeResponse.getCommission());
    }
  }

//  private void updateDynamicFeeInfo(List<TxnRequest> dynamicRequest, TxnResponse txnResponse) {
//    if (!CollectionUtils.isEmpty(dynamicRequest) && dynamicRequest.size() > 1) {
//      TxnRequest request = dynamicRequest.get(0);
//      if (request.getTxnType() == TransactionType.CASH_OUT_TO_MFS_AGENT
//        || request.getTxnType() == TransactionType.FUND_TRANSFER) {
//        TxnResponse feeResponse = estimateDynamicTxnFee(dynamicRequest);
//        txnResponse.setFee(feeResponse.getFee());
//        txnResponse.setCommission(feeResponse.getCommission());
//      }
//    }
//  }

  private void updateDynamicFeeInfo(TxnRequest dynamicTxnRequest, TxnResponse txnResponse, Map<String, MfsTxnType> mfsBulkTxnTypes, List<TxnRequest> txnFeesAndCommissions) {
    if (mfsBulkTxnTypes.get(dynamicTxnRequest.getTxnType()).isIncludeFeeInResponse()) {  
      TxnResponse feeResponse = estimateDynamicTxnFee(dynamicTxnRequest, txnFeesAndCommissions, mfsBulkTxnTypes);
      txnResponse.setFee(feeResponse.getFee());
      txnResponse.setCommission(feeResponse.getCommission());
    }
  }

//  public TxnResponse estimateDynamicTxnFee(List<TxnRequest> dynamicRequest) {
//    BigDecimal feeTotalAmount = BigDecimal.ZERO;
//    String fromAc;
//    TxnResponse response = new TxnResponse();
//    
//    if (!CollectionUtils.isEmpty(dynamicRequest) && dynamicRequest.size() > 1) {
//      fromAc = dynamicRequest.get(0).getFromAc();
//      response.setAmount(dynamicRequest.get(0).getAmount());
//      for (int i=1; i<dynamicRequest.size(); i++) {
//        TxnRequest request = dynamicRequest.get(i);
//        if (!"SYSTEM".equalsIgnoreCase(fromAc) && fromAc.equalsIgnoreCase(request.getFromAc()) 
//          && (request.getTxnType() == TransactionType.SERVICE_FEE_CASH_OUT)) {
//          feeTotalAmount = feeTotalAmount.add(request.getAmount());
//        }
//      }
//    }
//    if (feeTotalAmount.compareTo(BigDecimal.ZERO) > 0) {
//      response.setFee(feeTotalAmount);
//    }
//    response.setCommission(null);
//    response.setBalanceFrom(null);
//    response.setBalanceTo(null);
//    response.setTicket(null);
//    response.setTraceNo(null);
//    return response;
//  }
  
  public TxnResponse estimateDynamicTxnFee(TxnRequest txnRequest, List<TxnRequest> feesAndCommissionRequests, Map<String, MfsTxnType> mfsBulkTxnTypes) {
    BigDecimal feeTotalAmount = BigDecimal.ZERO;
    BigDecimal commissionTotalAmount = BigDecimal.ZERO;
    String fromAc = txnRequest.getFromAc();
    TxnResponse response = new TxnResponse();
    for (TxnRequest feeRequest: feesAndCommissionRequests) {
      MfsTxnType feeTxnType = mfsBulkTxnTypes.get(feeRequest.getTxnType());
      if (feeTxnType.getParentType() != null) {
        if (feeTxnType.getTypeCategory().equals(MfsTxnType.TypeCategory.FEE) 
          && !"SYSTEM".equalsIgnoreCase(fromAc)
          && fromAc.equalsIgnoreCase(feeRequest.getFromAc())) {
          feeTotalAmount = feeTotalAmount.add(feeRequest.getAmount());
        } else if (feeTxnType.getTypeCategory().equals(MfsTxnType.TypeCategory.COMMISSION) && "SYSTEM".equalsIgnoreCase(fromAc)) {
          commissionTotalAmount.add(feeRequest.getAmount());
        }
      }
    }
    response.setFee(feeTotalAmount);
    response.setCommission(commissionTotalAmount);
    response.setBalanceFrom(null);
    response.setBalanceTo(null);
    response.setTicket(null);
    response.setTraceNo(null);
    return response;
  }

  private List<TxnResponse> processBulkPaymentResponse(List<TxnPair> txnPairs, Map<String, MfsTxnType> mfsBulkTxnTypes, Map<String, List<TxnRequest>> dynamicFeesAndCommissions) {
    List<TxnResponse> txnResultList = new LinkedList<>();
    for (TxnPair txnPair : txnPairs) {
      TxnRequest txnRequest = txnPair.getRequest();
      TxnResponse txnResult;
      BulkPaymentResult paymentResult = txnPair.getPaymentResult();
      Transfer transfer = (Transfer) paymentResult.getPayment();
      if (transfer != null && transfer.getTransactionNumber() != null) {
        txnResult = cyclosMiddleware.convertTxnResult(transfer, txnPair.getRequest());
      } else {
        throw paymentResult.getException();
      }
      txnResultList.add(txnResult);
      MfsTxnType mfsTxnType = mfsBulkTxnTypes.get(txnRequest.getTxnType());
      if (StringUtils.isNotBlank(txnRequest.getRequestId()) && dynamicFeesAndCommissions.containsKey(txnRequest.getRequestId())) {
          updateDynamicFeeInfo(txnRequest, txnResult, mfsBulkTxnTypes, dynamicFeesAndCommissions.get(txnRequest.getRequestId()));
      } else {
          updateFeeInfo(txnRequest, txnResult, mfsTxnType);
      }
      includeBalances(txnPair.getRequest(), txnResult, mfsTxnType);
    }
    return txnResultList;
  }
  
//  private List<TxnResponse> processBulkPaymentResponse(List<BulkPaymentResult> bulkPaymentResultList, Map<String, MfsTxnType> mfsBulkTxnTypes) {
//    List<TxnResponse> txnResultList = new LinkedList<>();
//    for (BulkPaymentResult bulkPaymentResult : bulkPaymentResultList) {
//      TxnResponse txnResult = new TxnResponse();
//      Transfer transfer = (Transfer) bulkPaymentResult.getPayment();
//      if (transfer != null && transfer.getTransactionNumber() != null) {
//        txnResult.setStatus(MfsConstant.STATUS_PROCESSED);
//        txnResult.setAmount(transfer.getAmount());
//        txnResult.setTxnId(transfer.getTransactionNumber());
//        txnResult.setFromAccount(transfer.getFrom().getOwnerName());
//        txnResult.setToAccount(transfer.getTo().getOwnerName());
//        txnResult.setTraceNo(transfer.getTraceData());
//        txnResult.setSystemWiseTxnId(transfer.getSystemWiseTxnId());
//        if (StringUtils.isNotBlank(transfer.getMfsTransactionType())) {
//          TxnRequest txnRequest = new TxnRequest();
//          txnRequest.setTxnType(TransactionType.valueOf(transfer.getMfsTransactionType()));
//          if (transfer.isToSystem()) {
//            txnRequest.setToAc("SYSTEM"); 
//          } else {
//            txnRequest.setToAc(transfer.getTo().getOwnerName());
//          }
//          if (transfer.isFromSystem()) {
//              txnRequest.setFromAc("SYSTEM");
//          } else {
//              txnRequest.setFromAc(transfer.getFrom().getOwnerName());
//          }
//          updateFeeInfo(txnRequest, txnResult, mfsBulkTxnTypes.get(txnRequest.getTxnType()));
//          includeBalances(txnRequest, txnResult, mfsBulkTxnTypes.get(txnRequest.getTxnType()));
//        }
//      } else {
//        throw bulkPaymentResult.getException();
//      }
//      txnResultList.add(txnResult);
//    }
//    return txnResultList;
//  }

  private TxnResponse processDynamicPaymentResponse(TxnRequest mainTxnRequest, List<TxnPair> txnPairs, Map<String, MfsTxnType> mfsBulkTxnTypes, Map<String, List<TxnRequest>> dynamicFeesAndCommissions) {
    List<TxnResponse> txnResultList = new ArrayList<>();
    TxnResponse dynamicResponse = new TxnResponse();
    String mainTxnRequestId = mainTxnRequest.getRequestId();
    for (TxnPair txnPair : txnPairs) {
      TxnRequest txnRequest = txnPair.getRequest();
      TxnResponse txnResult;
      BulkPaymentResult paymentResult = txnPair.getPaymentResult();
      Transfer transfer = (Transfer) paymentResult.getPayment();
      if (transfer != null && transfer.getTransactionNumber() != null) {
          txnResult = cyclosMiddleware.convertTxnResult(transfer, txnPair.getRequest());
      } else {
        throw paymentResult.getException();
      }
      txnResultList.add(txnResult);
      if (StringUtils.isNotBlank(txnRequest.getRequestId()) && mainTxnRequestId.equals(txnRequest.getRequestId())) {
          dynamicResponse = txnResult;
          if (dynamicFeesAndCommissions.containsKey(txnRequest.getRequestId())) {
            updateDynamicFeeInfo(txnRequest, dynamicResponse, mfsBulkTxnTypes, dynamicFeesAndCommissions.get(txnRequest.getRequestId()));
          }
      }
    }
    includeBalances(mainTxnRequest, dynamicResponse, mfsBulkTxnTypes.get(mainTxnRequest.getTxnType()));
    return dynamicResponse;
  }

//  private List<TxnResponse> processDynamicPaymentResponse(List<TxnPair> txnPairs, List<TxnRequest> dynamicRequest) {
//    List<TxnResponse> txnResultList = new LinkedList<>();
//    for (TxnPair txnPair : txnPairs) {
//      TxnResponse txnResult;
//      BulkPaymentResult paymentResult = txnPair.getPaymentResult();
//      Transfer transfer = (Transfer) paymentResult.getPayment();
//      if (transfer != null && transfer.getTransactionNumber() != null) {
//        txnResult = cyclosMiddleware.convertTxnResult(transfer, txnPair.getRequest());
//      } else {
//        throw paymentResult.getException();
//      }
//      txnResultList.add(txnResult);
//      includeBalances(txnPair.getRequest(), txnResult);
//    }
//    if (txnResultList.size() > 1) {
//      updateDynamicFeeInfo(dynamicRequest, txnResultList.get(0));
//    }
//    return txnResultList;
//  }

  private void validateReversalTxn(TxnReversalRequest request, Transfer transfer) {
    //todo validate chargeback
    if (transfer == null) {
      throw new MFSCommonException(TRANSACTION_NOT_FOUND, ERROR_MAP.get(ErrorConstants.TRANSACTION_NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    if (request.getAmount().compareTo(transfer.getAmount()) != 0) {
      throw new MFSCommonException(AMOUNT_NOT_MATCHED, ERROR_MAP.get(AMOUNT_NOT_MATCHED), HttpStatus.BAD_REQUEST);
    }
  }

  private void includeBalances(TxnRequest request, TxnResponse txnResponse, MfsTxnType txnType) {
//    if (request.getTxnType() == TransactionType.ADD_MONEY
//        || request.getTxnType() == TransactionType.ADD_MONEY_SSL
//        || request.getTxnType() == TransactionType.ADD_MONEY_FIRSTCASH_FROM_FSIBL
//        || request.getTxnType() == TransactionType.BANK_CASHIN
//        || request.getTxnType() == TransactionType.BANK_CASHIN_MERCHANT
//        || request.getTxnType() == TransactionType.PAY_DISTRIBUTOR_COMMISSION
//        || request.getTxnType() == TransactionType.SALARY_DISBURSEMENT
//        || request.getTxnType() == TransactionType.BULK_PAYMENT
//        || request.getTxnType() == TransactionType.REMITTANCE_PAYMENT
//        || request.getTxnType() == TransactionType.REMITTANCE_INCENTIVE) { //Include to account balance
//    }
    if (txnType.isIncludeToAcBalanceInResponse()) { //Include to account balance
        BalanceResponse toAccountBalance = accountService.getCurrentBalance(txnResponse.getToAccount(), null);
        txnResponse.setBalanceTo(toAccountBalance.getAvailableBalance());
    }

//    if (request.getTxnType() == TransactionType.AGENT_ASSISTED_PAYMENT
//        || request.getTxnType() == TransactionType.BANK_CASHOUT
//        || request.getTxnType() == TransactionType.BANK_CASHOUT_MERCHANT
//        || request.getTxnType() == TransactionType.BANK_CASHOUT_CUSTOMER
//        || request.getTxnType() == TransactionType.CASH_IN_FROM_MFS_AGENT
//        || request.getTxnType() == TransactionType.CASH_OUT_TO_MFS_AGENT
//        || request.getTxnType() == TransactionType.CASHOUT_AGENT_DISTRIBUTOR
//        || request.getTxnType() == TransactionType.MOBILE_RECHARGE
//        || request.getTxnType() == TransactionType.PAYMENT
//        || request.getTxnType() == TransactionType.SEND_MONEY
//        || request.getTxnType() == TransactionType.TOPUP_AGENT
//        || request.getTxnType() == TransactionType.UTILITY_BILL_PAYMENT_WASA_SSL
//        || request.getTxnType() == TransactionType.FUND_TRANSFER) { //Include from account balance
//    }
    if (txnType.isIncludeFromAcBalanceInResponse()) { //Include from account balance
        BalanceResponse fromAccountBalance = accountService.getCurrentBalance(txnResponse.getFromAccount(), null);
        txnResponse.setBalanceFrom(fromAccountBalance.getAvailableBalance());
    }
  }

  private void walletLogin(TxnRequest request, MfsTxnType txnType) {
    if (StringUtils.isNotBlank(request.getByAc()) && !"SYSTEM".equalsIgnoreCase(request.getByAc()) && txnType.isByAcPinEnabled()) {
        vaidateRequestedPinInput(request);
        accountService.loginUser(new CheckPinRequest(request.getByAc(), request.getPin()));
    }
    else if (!"SYSTEM".equalsIgnoreCase(request.getFromAc()) && txnType.isFromAcPinEnabled() 
            && (!request.isScheduledPayment() || !txnType.isScheduledPayment())) {
        vaidateRequestedPinInput(request);
        accountService.loginUser(new CheckPinRequest(request.getFromAc(), request.getPin()));
    }
  }
}
