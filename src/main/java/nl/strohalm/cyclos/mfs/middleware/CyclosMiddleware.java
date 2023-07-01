package nl.strohalm.cyclos.mfs.middleware;

import com.google.common.base.Strings;

import nl.strohalm.cyclos.dao.accounts.transactions.TransferDAO;
import nl.strohalm.cyclos.entities.access.Channel;
import nl.strohalm.cyclos.entities.access.MemberUser;
import nl.strohalm.cyclos.entities.access.PrincipalType;
import nl.strohalm.cyclos.entities.accounts.Account;
import nl.strohalm.cyclos.entities.accounts.MemberAccount;
import nl.strohalm.cyclos.entities.accounts.SystemAccountOwner;
import nl.strohalm.cyclos.entities.accounts.transactions.Payment;
import nl.strohalm.cyclos.entities.accounts.transactions.Transfer;
import nl.strohalm.cyclos.entities.accounts.transactions.TransferType;
import nl.strohalm.cyclos.entities.customization.fields.MemberCustomField;
import nl.strohalm.cyclos.entities.customization.fields.MemberCustomFieldValue;
import nl.strohalm.cyclos.entities.exceptions.EntityNotFoundException;
import nl.strohalm.cyclos.entities.groups.MemberGroup;
import nl.strohalm.cyclos.entities.members.Element;
import nl.strohalm.cyclos.entities.members.Member;
import nl.strohalm.cyclos.mfs.entities.MfsGroupConfig;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType.TxnTypeTag;
import nl.strohalm.cyclos.mfs.exceptions.ErrorConstants;
import nl.strohalm.cyclos.mfs.exceptions.MFSCommonException;
import nl.strohalm.cyclos.mfs.models.accounts.AcRegRequest;
import nl.strohalm.cyclos.mfs.models.accounts.UpdateAccountRequest;
import nl.strohalm.cyclos.mfs.models.accounts.WalletInfoResponse;
import nl.strohalm.cyclos.mfs.models.enums.AccountStatus;
import nl.strohalm.cyclos.mfs.models.enums.AccountType;
import nl.strohalm.cyclos.mfs.models.enums.TransactionType;
import nl.strohalm.cyclos.mfs.models.transactions.BulkTxnRequest;
import nl.strohalm.cyclos.mfs.models.transactions.TxnRequest;
import nl.strohalm.cyclos.mfs.models.transactions.TxnResponse;
import nl.strohalm.cyclos.mfs.services.MfsGroupService;
import nl.strohalm.cyclos.mfs.services.MfsTxnTypeService;
import nl.strohalm.cyclos.mfs.utils.AcTypeGroupConstant;
import nl.strohalm.cyclos.mfs.utils.MfsConstant;
import nl.strohalm.cyclos.mfs.utils.TxnTypeConstant;
import nl.strohalm.cyclos.services.access.ChannelServiceLocal;
import nl.strohalm.cyclos.services.accounts.AccountDateDTO;
import nl.strohalm.cyclos.services.accounts.AccountServiceLocal;
import nl.strohalm.cyclos.services.customization.MemberCustomFieldServiceLocal;
import nl.strohalm.cyclos.services.elements.ElementServiceLocal;
import nl.strohalm.cyclos.services.fetch.FetchServiceLocal;
import nl.strohalm.cyclos.services.groups.GroupServiceLocal;
import nl.strohalm.cyclos.services.transactions.DoPaymentDTO;
import nl.strohalm.cyclos.services.transactions.TransactionContext;
import nl.strohalm.cyclos.services.transfertypes.TransferTypeServiceLocal;
import nl.strohalm.cyclos.utils.CustomFieldHelper;
import nl.strohalm.cyclos.utils.RelationshipHelper;
import nl.strohalm.cyclos.webservices.model.RegistrationFieldValueVO;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import static nl.strohalm.cyclos.mfs.utils.MfsConstant.DEBIT_ALLOWED_STATUS;
import static nl.strohalm.cyclos.mfs.utils.MfsConstant.TXN_BLOCK_STATUS;

@Service
public class CyclosMiddleware {
  private static final Logger logger = LogManager.getLogger(CyclosMiddleware.class);

  @Autowired
  ElementServiceLocal elementServiceLocal;
  @Autowired
  GroupServiceLocal groupServiceLocal;
  @Autowired
  TransferTypeServiceLocal transferTypeServiceLocal;
  @Autowired
  ChannelServiceLocal channelServiceLocal;
  @Autowired
  AccountServiceLocal accountServiceLocal;

  @Autowired
  MfsGroupService mfsGroupService;
  @Autowired
  MfsTxnTypeService mfsTxnTypeService;
  @Autowired
  MemberCustomFieldServiceLocal memberCustomFieldServiceLocal;
  @Autowired
  CustomFieldHelper customFieldHelper;

  public DoPaymentDTO getValidateCyclosDoPaymentDTO(TxnRequest txnRequest) {
    DoPaymentDTO doPaymentDTO = new DoPaymentDTO();
    Member fromMember = null;
    Member toMember = null;
    Member byMember = null;
    String fromAcType = null;
    String toAcType = null;

    try {
      PrincipalType principalType = new PrincipalType(Channel.Principal.USER);
      if (!"SYSTEM".equalsIgnoreCase(txnRequest.getFromAc())) {
        try {
          fromMember = elementServiceLocal.loadByPrincipal(principalType, txnRequest.getFromAc(),
            Element.Relationships.USER, Element.Relationships.GROUP);
        } catch (EntityNotFoundException ex) {
          throw new MFSCommonException(ErrorConstants.FROM_AC_NOT_FOUND, ErrorConstants.ERROR_MAP.get(ErrorConstants.FROM_AC_NOT_FOUND), HttpStatus.BAD_REQUEST);
        }
        doPaymentDTO.setFrom(fromMember);

        //check status
        Account fromAc = null;
        for (final Account ac : accountServiceLocal.getAccounts(fromMember)) {
          fromAc = ac;
        }
        if (fromAc == null) {
          throw new MFSCommonException(ErrorConstants.ACCOUNT_NOT_FOUND,ErrorConstants.ERROR_MAP.get(ErrorConstants.ACCOUNT_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        validateStatus((MemberAccount) fromAc, true);
      } else {
        doPaymentDTO.setFrom(SystemAccountOwner.instance());

      }

      if (!"SYSTEM".equalsIgnoreCase(txnRequest.getToAc())) {
        try {
          toMember = elementServiceLocal.loadByPrincipal(principalType, txnRequest.getToAc(),
            Element.Relationships.USER, Element.Relationships.GROUP);
        } catch (EntityNotFoundException e) {
          throw new MFSCommonException(ErrorConstants.TO_AC_NOT_FOUND, ErrorConstants.ERROR_MAP.get(ErrorConstants.TO_AC_NOT_FOUND) + ":" + txnRequest.getToAc(), HttpStatus.BAD_REQUEST);
        }
        doPaymentDTO.setTo(toMember);
        //check status
        Account toAc = null;
        for (final Account ac : accountServiceLocal.getAccounts(toMember)) {
          toAc = ac;
        }
        if (toAc == null) {
          throw new MFSCommonException(ErrorConstants.ACCOUNT_NOT_FOUND, ErrorConstants.ERROR_MAP.get(ErrorConstants.ACCOUNT_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        validateStatus((MemberAccount) toAc, false);
      } else {
        doPaymentDTO.setTo(SystemAccountOwner.instance());
      }
      // By Ac check
      if (StringUtils.isNotBlank(txnRequest.getByAc()) && !"SYSTEM".equalsIgnoreCase(txnRequest.getByAc())) {
          try {
            byMember = elementServiceLocal.loadByPrincipal(principalType, txnRequest.getByAc(),
              Element.Relationships.USER, Element.Relationships.GROUP);
          } catch (EntityNotFoundException ex) {
            throw new MFSCommonException(ErrorConstants.BY_AC_NOT_FOUND, ErrorConstants.ERROR_MAP.get(ErrorConstants.BY_AC_NOT_FOUND), HttpStatus.BAD_REQUEST);
          }
          doPaymentDTO.setBy(byMember);

          //check status
          Account byAc = null;
          for (final Account ac : accountServiceLocal.getAccounts(byMember)) {
            byAc = ac;
          }
          if (byAc == null) {
            throw new MFSCommonException(ErrorConstants.ACCOUNT_NOT_FOUND,ErrorConstants.ERROR_MAP.get(ErrorConstants.ACCOUNT_NOT_FOUND), HttpStatus.NOT_FOUND);
          }
          //validateStatus((MemberAccount) byAc, true);
        } 
      
      TransferType transferType = resolveCoreTxnType(txnRequest);

      doPaymentDTO.setTransferType(transferType);
      doPaymentDTO.setDescription(getPaymentDescription(txnRequest, transferType, fromMember, toMember));
      doPaymentDTO.setAmount(txnRequest.getAmount());
      doPaymentDTO.setContext(TransactionContext.AUTOMATIC);
      doPaymentDTO.setShowScheduledToReceiver(false);
      doPaymentDTO.setChannel(Channel.REST);
      doPaymentDTO.setTraceData(txnRequest.getTraceData());
      doPaymentDTO.setNote(txnRequest.getNote());
      doPaymentDTO.setInvoiceNo(txnRequest.getInvoiceNo());
      doPaymentDTO.setCustomerRefId(txnRequest.getCustomerRefId());
      doPaymentDTO.setParentTraceData(txnRequest.getParentRequestId());
      doPaymentDTO.setMfsTransactionType(txnRequest.getTxnType().toString());
      doPaymentDTO.setExternalCustomer(txnRequest.getExternalCustomer());
      doPaymentDTO.setSystemWiseTxnId(txnRequest.getRequestId());
      return doPaymentDTO;
    } catch (EntityNotFoundException e) {
      throw new MFSCommonException(ErrorConstants.FROM_AC_NOT_FOUND, e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  private void validateStatus(MemberAccount memberAccount, boolean fromFlag) {
    if (fromFlag) {
      if (!DEBIT_ALLOWED_STATUS.contains(memberAccount.getStatus())) {
        throw new MFSCommonException(ErrorConstants.FROM_AC_STATUS_NOT_VALID, ErrorConstants.ERROR_MAP.get(ErrorConstants.FROM_AC_STATUS_NOT_VALID), HttpStatus.BAD_REQUEST);
      }
    } else {
      if (TXN_BLOCK_STATUS.contains(memberAccount.getStatus())) {
        throw new MFSCommonException(ErrorConstants.AC_STATUS_NOT_VALID, ErrorConstants.ERROR_MAP.get(ErrorConstants.AC_STATUS_NOT_VALID), HttpStatus.BAD_REQUEST);
      }
    }

  }

  private void validateFromPin(MemberAccount memberAccount, String PIN) {
    if (PIN == null || PIN.length() < 4) {
        throw new MFSCommonException(ErrorConstants.INVALID_PIN, ErrorConstants.ERROR_MAP.get(ErrorConstants.INVALID_PIN), HttpStatus.BAD_REQUEST);
    }

  }

  public String getPaymentDescription(TxnRequest txnRequest, TransferType transferType, Member fromMember, Member toMember) {
    String note = "";
    String customerRefId = "";
    String byAc = "";
    String externalCustomer = "";
    String invoiceNo = "";
    String txnTag = "";
    String batchId = "";
    String maker = "";
    String checker = "";

    if (StringUtils.isNotEmpty(txnRequest.getByAc())) {
      byAc = ",byAc: " + txnRequest.getByAc();
    }
    if (StringUtils.isNotEmpty(txnRequest.getMaker())) {
      maker = ",maker: " + txnRequest.getMaker();
    }
    if (StringUtils.isNotEmpty(txnRequest.getChecker())) {
      checker = ",checker: " + txnRequest.getChecker();
    }
    if (StringUtils.isNotEmpty(txnRequest.getCustomerRefId())) {
      customerRefId = ",customerRefId: " + txnRequest.getCustomerRefId();
    }
    if (StringUtils.isNotEmpty(txnRequest.getInvoiceNo())) {
      invoiceNo = ",invoiceNo: " + txnRequest.getInvoiceNo();
    }
    if (StringUtils.isNotEmpty(txnRequest.getExternalCustomer())) {
      externalCustomer = ",externalCustomer: " + txnRequest.getExternalCustomer();
    }
    if (StringUtils.isNotEmpty(txnRequest.getNote())) {
      note = ",note: " + txnRequest.getNote();
    }
    if (StringUtils.isNotEmpty(txnRequest.getTxnTag())) {
      txnTag = ",txnTag: " + txnRequest.getTxnTag();
    }
    String description = transferType.getDescription() + ". [From Ac: " + txnRequest.getFromAc() + ", To Ac:" + txnRequest.getToAc() +
      "" + txnTag + "" + byAc + "" + maker + "" + "" + checker + "" +customerRefId + "" + invoiceNo + "" + externalCustomer + "" + note + "" + batchId + "]";
    return description;
  }

  public TxnResponse convertTxnResult(Payment payment, TxnRequest request) {
    Transfer transfer = (Transfer) payment;
    TxnResponse result = new TxnResponse();
    result.setStatus("PROCESSED");
    result.setMessage("Txn Successful");
    result.setAmount(transfer.getAmount());
    result.setFromAccount(transfer.getFrom().getOwnerName());
    result.setToAccount(transfer.getTo().getOwnerName());
    result.setTxnId(transfer.getTransactionNumber());
    result.setTxnType(request != null ? request.getTxnType().name() : transfer.getMfsTransactionType());
    result.setTxnTypeName(request != null ? request.getTxnType().getDescription() : transfer.getMfsTransactionType());
    result.setInvoiceNo(transfer.getInvoiceNo());
    result.setCustomerRefId(transfer.getCustomerRefId());
    result.setNote(request != null ? request.getNote() : transfer.getDescription());
    result.setExternalCustomer(transfer.getExternalCustomer());
    result.setTxnTime(transfer.getProcessDate().getTime());
    result.setSystemWiseTxnId(transfer.getSystemWiseTxnId());
    return result;
  }

  public List<DoPaymentDTO> getBulkPaymentDTOList(BulkTxnRequest bulkTxnRequest) {
    List<DoPaymentDTO> doPaymentDTOList = new LinkedList<DoPaymentDTO>();
    for (TxnRequest txnRequest : bulkTxnRequest.getTxnRequestList()) {
      DoPaymentDTO doPaymentDTO = getValidateCyclosDoPaymentDTO(txnRequest);
      doPaymentDTO.setMfsTransactionType(txnRequest.getTxnType().toString());
      doPaymentDTOList.add(doPaymentDTO);
    }
    return doPaymentDTOList;
  }


  public Member getMemberFromRequest(AcRegRequest regRequest) {
    final MemberUser user = new MemberUser();
    user.setUsername(regRequest.getWalletNo());
    final Member member = new Member();
    MemberGroup group;
    AccountType accountType = regRequest.getAccountType();
    MfsGroupConfig mfsGroupConfig = mfsGroupService.findByName(accountType.name());
    group = groupServiceLocal.load(mfsGroupConfig.getGroupId());
    member.setGroup(group);
    member.setUser(user);
    member.setName(regRequest.getFullName());
    member.setEmail(regRequest.getEmail());
    member.setAccountStatus(regRequest.getAccountStatus() != null ? regRequest.getAccountStatus() : AccountStatus.PENDING);
    member.getUser().setPassword(regRequest.getPassword());
    if (StringUtils.isNotEmpty(regRequest.getPin())) {
      member.getMemberUser().setPin(regRequest.getPin());
    }
    List<MemberCustomField> fields = memberCustomFieldServiceLocal.list();
    fields = customFieldHelper.onlyForGroup(fields, group);
    final Collection<MemberCustomFieldValue> fieldValues = customFieldHelper.toValueCollection(fields, regRequest.getFields());
    member.setCustomValues(fieldValues);
    return member;
  }

  public MemberUser getMemberUser(String walletNo) {
    try {
      final PrincipalType principalType = channelServiceLocal
        .resolvePrincipalType(MfsConstant.CHANNEL, MfsConstant.PRINCIPAL_TYPE);
      final Member member = elementServiceLocal.loadByPrincipal(principalType, walletNo,
        Element.Relationships.USER, Element.Relationships.GROUP);
      if (member != null) {
        return member.getMemberUser();
      }
    } catch (Exception e) {
      logger.warn("Exception during getMemberUser: " + e.getMessage());
    }
    return null;
  }

  public Member getMember(String walletNo) {
    try {
      final PrincipalType principalType = channelServiceLocal.resolvePrincipalType(
        MfsConstant.CHANNEL, MfsConstant.PRINCIPAL_TYPE);
      return elementServiceLocal.loadByPrincipal(principalType, walletNo,
        Element.Relationships.USER, Element.Relationships.GROUP);
    } catch (Exception ex) {
      logger.error("Unable to find member: " + ex.getMessage());
    }
    return null;
  }

  public Member prepareMemberCustomFiledstoUpdate(Member member, UpdateAccountRequest userRequest) {
      // Merge the custom fields
      List<RegistrationFieldValueVO> fieldValueVOs = userRequest.getFields();
      List<MemberCustomField> allowedFields = customFieldHelper.onlyForGroup(memberCustomFieldServiceLocal.list(), member.getMemberGroup());
      Collection<MemberCustomFieldValue> newFieldValues = customFieldHelper.mergeFieldValues(member, fieldValueVOs, allowedFields);
      member.setCustomValues(newFieldValues);
      return member;
  }

  public WalletInfoResponse getWalletInfoResponse(Member member, Account account) {
    WalletInfoResponse info = new WalletInfoResponse();
    MemberAccount memberAccount = (MemberAccount) account;
    info.setStatus(MfsConstant.STATUS_SUCCESS);
    info.setWalletNo(member.getUsername());
    info.setName(member.getName());
    for (Map.Entry entry : AcTypeGroupConstant.acTypeGroups.entrySet()) {
      if (member.getGroup().getId().equals(entry.getValue()))
        info.setUserType((AccountType) entry.getKey());
    }

    info.setAccountStatus(AccountStatus.valueOf(memberAccount.getStatus().name()));
    return info;
  }


  private void getAccountBalances(TxnResponse result, Transfer transfer) {
    AccountDateDTO dtoParams = new AccountDateDTO(transfer.getFrom());
    BigDecimal balance = accountServiceLocal.getBalance(dtoParams);
    result.setBalanceFrom(balance);

    dtoParams = new AccountDateDTO(transfer.getTo());
    balance = accountServiceLocal.getBalance(dtoParams);
    result.setBalanceTo(balance);
  }


  public Account getAccount(String walletNo) {
    final Member member = getMember(walletNo);
    if (member == null) {
      throw new MFSCommonException(ErrorConstants.ACCOUNT_NOT_FOUND, ErrorConstants.ERROR_MAP.get(ErrorConstants.ACCOUNT_NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    Account account = null;
    for (final Account ac : accountServiceLocal.getAccounts(member)) {
      account = ac;
    }
    if (account == null) {
      throw new MFSCommonException(ErrorConstants.ACCOUNT_NOT_FOUND, ErrorConstants.ERROR_MAP.get(ErrorConstants.ACCOUNT_NOT_FOUND), HttpStatus.NOT_FOUND);

    }
    return account;
  }

  private TransferType resolveCoreTxnType(TxnRequest txnRequest) {
    MfsTxnType mfsTxnType = null;
    if (txnRequest.getTxnTypeTag() == null) {
      mfsTxnType  = mfsTxnTypeService.findByName(txnRequest.getTxnType().name());
    } else {
      mfsTxnType = mfsTxnTypeService.findByNameAndTypeTag(txnRequest.getTxnType().name(), txnRequest.getTxnTypeTag());
    }
    if (mfsTxnType == null) {
      throw new MFSCommonException(ErrorConstants.TXN_TYPE_NOT_FOUND, ErrorConstants.ERROR_MAP.get(ErrorConstants.TXN_TYPE_NOT_FOUND), HttpStatus.BAD_REQUEST);
    }  
    TransferType transferType = transferTypeServiceLocal.load(mfsTxnType.getCoreTxnTypeId());
    if (transferType == null) {
      throw new MFSCommonException(ErrorConstants.TXN_TYPE_NOT_FOUND, ErrorConstants.ERROR_MAP.get(ErrorConstants.TXN_TYPE_NOT_FOUND), HttpStatus.BAD_REQUEST);
    }
    return transferType;
  }
}
