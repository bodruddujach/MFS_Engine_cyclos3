package nl.strohalm.cyclos.mfs.services;


import nl.strohalm.cyclos.entities.accounts.AccountType;
import nl.strohalm.cyclos.entities.accounts.fees.transaction.SimpleTransactionFee;
import nl.strohalm.cyclos.entities.accounts.fees.transaction.TransactionFee;
import nl.strohalm.cyclos.entities.accounts.fees.transaction.TransactionFeeQuery;
import nl.strohalm.cyclos.entities.accounts.transactions.Payment;
import nl.strohalm.cyclos.entities.accounts.transactions.Transfer;
import nl.strohalm.cyclos.mfs.exceptions.ErrorConstants;
import nl.strohalm.cyclos.mfs.exceptions.MFSCommonException;
import nl.strohalm.cyclos.mfs.middleware.CyclosMiddleware;
import nl.strohalm.cyclos.mfs.models.accounts.CheckPinRequest;

import nl.strohalm.cyclos.mfs.models.accounts.WalletStatementResp;
import nl.strohalm.cyclos.mfs.models.enums.TransactionType;
import nl.strohalm.cyclos.mfs.models.transactions.BulkTxnRequest;
import nl.strohalm.cyclos.mfs.models.transactions.TxnRequest;
import nl.strohalm.cyclos.mfs.models.transactions.TxnResponse;
import nl.strohalm.cyclos.mfs.models.transactions.TxnReversalRequest;
import nl.strohalm.cyclos.mfs.models.transactions.TxnReversalResponse;
import nl.strohalm.cyclos.mfs.utils.MfsConstant;
import nl.strohalm.cyclos.services.transactions.BulkPaymentResult;
import nl.strohalm.cyclos.services.transactions.DoPaymentDTO;
import nl.strohalm.cyclos.services.transactions.PaymentServiceLocal;
import nl.strohalm.cyclos.services.transfertypes.TransactionFeeServiceLocal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

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

  //todo autowire limit service

  public TxnResponse processTransaction(TxnRequest request) {
    DoPaymentDTO doPaymentDTO = cyclosMiddleware.getValidateCyclosDoPaymentDTO(request);

    if (!"SYSTEM".equalsIgnoreCase(request.getFromAc()) && checkPinEanable(request)) {
      accountService.loginUser(new CheckPinRequest(request.getFromAc(), request.getPin()));
    }
    // todo check limit validation
    Payment payment = paymentServiceLocal.doPayment(doPaymentDTO);
    // update limit
    TxnResponse txnResponse = cyclosMiddleware.convertTxnResult(payment, request);
    updateFeeInfo(request, txnResponse);
    return txnResponse;
  }

  private boolean checkPinEanable(TxnRequest request) {
    if ("****".equals(request.getPin()) && (request.getTxnType() == TransactionType.BANK_CASHOUT
      || request.getTxnType() == TransactionType.BANK_CASHOUT_MERCHANT
      || request.getTxnType() == TransactionType.TOPUP_AGENT)
    ) {
      return false;
    }
    return true;
  }

  private void updateFeeInfo(TxnRequest request, TxnResponse txnResponse) {
    if (request.getTxnType() == TransactionType.SEND_MONEY
      || request.getTxnType() == TransactionType.PAYMENT
      || request.getTxnType() == TransactionType.CASH_OUT_TO_MFS_AGENT
      || request.getTxnType() == TransactionType.BILL_PAYMENT
      || request.getTxnType() == TransactionType.AGENT_ASSISTED_PAYMENT) {

      TxnResponse feeResponse = estimate(request);
      txnResponse.setFee(feeResponse.getFee());
      txnResponse.setCommission(feeResponse.getCommission());
    }

  }

  public TxnResponse estimate(TxnRequest request) {
    final TransactionFeeQuery query = new TransactionFeeQuery();
    DoPaymentDTO doPaymentDTO = cyclosMiddleware.getValidateCyclosDoPaymentDTO(request);

    query.setTransferType(doPaymentDTO.getTransferType());
    final List<? extends TransactionFee> fees = transactionFeeService.search(query);
    BigDecimal feeTotalAmount = BigDecimal.ZERO;
    BigDecimal commissionTotalAmount = BigDecimal.ZERO;
    for (final TransactionFee fee : fees) {
      if (fee.getGeneratedTransferType().getFrom().getNature() == AccountType.Nature.MEMBER && (fee.getGeneratedTransferType().getFrom().getName().toLowerCase().contains(MfsConstant.CUSTOMER.toLowerCase())
        || fee.getGeneratedTransferType().getFrom().getName().toLowerCase().contains(MfsConstant.AGENT.toLowerCase()))) {
        if (fee instanceof SimpleTransactionFee && fee.getAmount().isPercentage()) {
          final BigDecimal feeValue = fee.getAmount().getValue();
          feeTotalAmount = request.getAmount().multiply(feeValue).divide(BigDecimal.valueOf(100));
        } else {
          feeTotalAmount = fee.getAmount().getValue();
        }
      }
      if (fee.getGeneratedTransferType().getTo().getNature() == AccountType.Nature.MEMBER && fee.getGeneratedTransferType().getTo().getName().toLowerCase().contains(MfsConstant.AGENT.toLowerCase())) {
        if (fee instanceof SimpleTransactionFee && fee.getAmount().isPercentage()) {
          final BigDecimal feeValue = fee.getAmount().getValue();
          commissionTotalAmount = request.getAmount().multiply(feeValue).divide(BigDecimal.valueOf(100));
        } else {
          commissionTotalAmount = fee.getAmount().getValue();
        }
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

  public WalletStatementResp getTxnDetail(String txnId) {
    return paymentServiceLocal.getTransactionDetails(txnId);
  }

  public List<TxnResponse> processBulkTxn(BulkTxnRequest bulkTxnRequest) {
    List<DoPaymentDTO> doPaymentDTOList = cyclosMiddleware.getBulkPaymentDTOList(bulkTxnRequest);
    List<BulkPaymentResult> bulkPaymentResultList = paymentServiceLocal.doBulkPayment(doPaymentDTOList);
    return processBulkPaymentResponse(bulkPaymentResultList);
  }

  private List<TxnResponse> processBulkPaymentResponse(List<BulkPaymentResult> bulkPaymentResultList) {
    List<TxnResponse> txnResultList = new LinkedList<TxnResponse>();
    for (BulkPaymentResult bulkPaymentResult : bulkPaymentResultList) {
      TxnResponse txnResult = new TxnResponse();
      Transfer transfer = (Transfer) bulkPaymentResult.getPayment();
      if (transfer != null && transfer.getTransactionNumber() != null) {
        txnResult.setStatus(MfsConstant.STATUS_PROCESSED);
        txnResult.setAmount(transfer.getAmount());
        txnResult.setTxnId(transfer.getTransactionNumber());
        txnResult.setFromAccount(transfer.getFrom().getOwnerName());
        txnResult.setToAccount(transfer.getTo().getOwnerName());
      } else {
        throw bulkPaymentResult.getException();
      }
      txnResultList.add(txnResult);
    }
    return txnResultList;
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

  private void validateReversalTxn(TxnReversalRequest request, Transfer transfer) {
    //todo validate chargeback
    if (transfer == null) {
      throw new MFSCommonException(TRANSACTION_NOT_FOUND, ERROR_MAP.get(ErrorConstants.TRANSACTION_NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    if (request.getAmount().compareTo(transfer.getAmount()) != 0) {
      throw new MFSCommonException(AMOUNT_NOT_MATCHED, ERROR_MAP.get(AMOUNT_NOT_MATCHED), HttpStatus.BAD_REQUEST);
    }
  }
}
