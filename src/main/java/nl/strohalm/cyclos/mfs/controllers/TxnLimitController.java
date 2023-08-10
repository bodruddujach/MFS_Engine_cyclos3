package nl.strohalm.cyclos.mfs.controllers;

import nl.strohalm.cyclos.entities.Relationship;
import nl.strohalm.cyclos.entities.accounts.MemberAccount;
import nl.strohalm.cyclos.entities.accounts.MemberAccountType;
import nl.strohalm.cyclos.entities.accounts.MemberGroupAccountSettings;
import nl.strohalm.cyclos.entities.accounts.transactions.TransferType;
import nl.strohalm.cyclos.entities.accounts.transactions.TransferTypeQuery;
import nl.strohalm.cyclos.entities.groups.MemberGroup;
import nl.strohalm.cyclos.mfs.entities.MfsAccountGenericLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsAccountTxnLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsGenericLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsTxnLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType;
import nl.strohalm.cyclos.mfs.models.transactions.AccountCategoryCreditLimitDTO;
import nl.strohalm.cyclos.mfs.models.transactions.AccountCategoryCreditLimitRequestDTO;
import nl.strohalm.cyclos.mfs.models.transactions.AccountCreditLimitDTO;
import nl.strohalm.cyclos.mfs.models.transactions.AccountGenericLimitResponse;
import nl.strohalm.cyclos.mfs.models.transactions.AccountTxnLimitRequest;
import nl.strohalm.cyclos.mfs.models.transactions.AccountTxnLimitResponse;
import nl.strohalm.cyclos.mfs.models.transactions.GenericLimitResponse;
import nl.strohalm.cyclos.mfs.models.transactions.TxnLimitRequest;
import nl.strohalm.cyclos.mfs.models.transactions.TxnLimitExchangeDTO;
import nl.strohalm.cyclos.mfs.models.transactions.TxnLimitResponse;
import nl.strohalm.cyclos.mfs.models.transactions.UpdateAccountGenericLimitRequest;
import nl.strohalm.cyclos.mfs.models.transactions.UpdateAccountTxnLimitRequest;
import nl.strohalm.cyclos.mfs.models.transactions.UpdateGenericLimitRequest;
import nl.strohalm.cyclos.mfs.models.transactions.UpdateTxnLimitRequest;
import nl.strohalm.cyclos.mfs.models.transactions.WalletLimitData;
import nl.strohalm.cyclos.mfs.models.transactions.WalletLimitResponse;
import nl.strohalm.cyclos.mfs.services.MfsAccountService;
import nl.strohalm.cyclos.mfs.services.MfsTxnTypeService;
import nl.strohalm.cyclos.mfs.services.TxnLimitService;
import nl.strohalm.cyclos.services.accounts.AccountTypeServiceLocal;
import nl.strohalm.cyclos.services.groups.GroupService;
import nl.strohalm.cyclos.services.groups.GroupServiceLocal;
import nl.strohalm.cyclos.services.transfertypes.TransferTypeServiceLocal;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("v1/api/limit")
public class TxnLimitController {

    @Autowired
    TxnLimitService txnLimitService;

    @Autowired
    MfsTxnTypeService mfsTxnTypeService;

    @Autowired
    GroupServiceLocal groupServiceLocal;

    @Autowired
    AccountTypeServiceLocal accountTypeServiceLocal;

    @Autowired
    TransferTypeServiceLocal transferTypeServiceLocal;

    @Autowired
    MfsAccountService mfsAccountService;

    @RequestMapping(value = "/config/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TxnLimitResponse loadTxnLimitConfig(@PathVariable Long id) {
        return txnLimitService.loadMfsTxnLimitConfig(id);
    }

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    @ResponseBody
    public List<MfsTxnLimitConfig> loadAllTxnLimitConfigs() {
        return txnLimitService.loadAllMfsTxnLimitConfigs();
    }

    @RequestMapping(value = "/config/active", method = RequestMethod.GET)
    @ResponseBody
    public List<MfsTxnLimitConfig> loadAllActiveTxnLimitConfigs() {
        return txnLimitService.loadAllMfsTxnLimitConfigsByStatus(true);
    }

    @RequestMapping(value = "/config/inactive", method = RequestMethod.GET)
    @ResponseBody
    public List<MfsTxnLimitConfig> loadAllInactiveTxnLimitConfigs() {
        return txnLimitService.loadAllMfsTxnLimitConfigsByStatus(false);
    }

    @RequestMapping(value = "/config", method = RequestMethod.POST)
    @ResponseBody
    public TxnLimitResponse saveTxnLimitConfig(@Validated @RequestBody TxnLimitRequest request) {
        MfsTxnLimitConfig txnLimitConfig = new MfsTxnLimitConfig();
        txnLimitConfig.setMinAmountPerTxn(request.getMinAmountPerTxn());
        txnLimitConfig.setMaxAmountPerTxn(request.getMaxAmountPerTxn());
        txnLimitConfig.setMaxNumberOfTxnPerDay(request.getMaxNumberOfTxnPerDay());
        txnLimitConfig.setMaxNumberOfTxnPerMonth(request.getMaxNumberOfTxnPerMonth());
        txnLimitConfig.setMaxAmountPerDay(request.getMaxAmountPerDay());
        txnLimitConfig.setMaxAmountPerMonth(request.getMaxAmountPerMonth());
        txnLimitConfig.setApplyOn(request.getApplyOn());
        MfsTxnType mfsTxnType = mfsTxnTypeService.findByName(request.getTxnType().name());
        TransferType transferType = transferTypeServiceLocal.load(mfsTxnType.getCoreTxnTypeId(), TransferType.Relationships.FROM, TransferType.Relationships.TO);
        txnLimitConfig.setMfsTypeName(mfsTxnType.getName());
        txnLimitConfig.setMfsTypeDescription(StringUtils.isBlank(request.getMfsTypeDescription()) ? mfsTxnType.getDescription() : request.getMfsTypeDescription());
        txnLimitConfig.setTransferType(transferType);
        txnLimitConfig.setFromAcType(transferType.getFrom().getName());
        txnLimitConfig.setToAcType(transferType.getTo().getName());
        txnLimitConfig.setEnable(request.isEnable());
        MemberGroup group = groupServiceLocal.load(request.getGroupId());
        txnLimitConfig.setGroup(group);
        if (StringUtils.isNotBlank(request.getGenericLimit())) {
            MfsGenericLimitConfig gnericLimitConfig = txnLimitService.loadGenericLimitConfigByNameAndEnabled(request.getGenericLimit(), true);
            txnLimitConfig.setGenericLimit(gnericLimitConfig);
        }
        return txnLimitService.saveMfsTxnLimitConfig(txnLimitConfig);
    }

    @RequestMapping(value = "/config", method = RequestMethod.PUT)
    @ResponseBody
    public TxnLimitResponse updateTxnLimitConfig(@Validated @RequestBody UpdateTxnLimitRequest txnLimitConfig) {
        return txnLimitService.updateMfsTxnLimitConfig(txnLimitConfig);
    }

    @RequestMapping(value = "/config/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteTxnLimitConfig(@PathVariable Long id) {
        return txnLimitService.deleteMfsTxnLimitConfig(id);
    }

    
    @RequestMapping(value = "/config/generic/{id}", method = RequestMethod.GET)
    @ResponseBody
    public MfsGenericLimitConfig loadGenericLimitConfig(@PathVariable Long id) {
        return txnLimitService.loadGenericLimitConfig(id);
    }

    @RequestMapping(value = "/config/generic", method = RequestMethod.GET)
    @ResponseBody
    public List<MfsGenericLimitConfig> loadAllGenericLimitConfigs() {
        return txnLimitService.loadAllMfsGenericLimitConfigs();
    }

    @RequestMapping(value = "/config/generic/active", method = RequestMethod.GET)
    @ResponseBody
    public List<MfsGenericLimitConfig> loadAllActiveGenericLimitConfigs() {
        return txnLimitService.loadAllMfsGenericLimitConfigsByStatus(true);
    }

    @RequestMapping(value = "/config/generic/inactive", method = RequestMethod.GET)
    @ResponseBody
    public List<MfsGenericLimitConfig> loadAllInactiveGenericLimitConfigs() {
        return txnLimitService.loadAllMfsGenericLimitConfigsByStatus(false);
    }

    @RequestMapping(value = "/config/generic", method = RequestMethod.POST)
    @ResponseBody
    public GenericLimitResponse saveGenericLimitConfig(@Validated @RequestBody MfsGenericLimitConfig request) {
        return txnLimitService.saveMfsGenericLimitConfig(request);
    }

    @RequestMapping(value = "/config/generic", method = RequestMethod.PUT)
    @ResponseBody
    public GenericLimitResponse updateGenericLimitConfig(@Validated @RequestBody UpdateGenericLimitRequest request) {
        return txnLimitService.updateMfsGenericLimitConfig(request);
    }
    // todo config list

    // todo customer limit list
    @RequestMapping(value = "/account/config", method = RequestMethod.POST)
    @ResponseBody
    public AccountTxnLimitResponse saveAccountTxnLimitConfig(@Validated @RequestBody AccountTxnLimitRequest request) {
        MfsAccountTxnLimitConfig accountTxnLimitConfig = new MfsAccountTxnLimitConfig();
        accountTxnLimitConfig.setMinAmountPerTxn(request.getMinAmountPerTxn());
        accountTxnLimitConfig.setMaxAmountPerTxn(request.getMaxAmountPerTxn());
        accountTxnLimitConfig.setMaxNumberOfTxnPerDay(request.getMaxNumberOfTxnPerDay());
        accountTxnLimitConfig.setMaxNumberOfTxnPerMonth(request.getMaxNumberOfTxnPerMonth());
        accountTxnLimitConfig.setMaxAmountPerDay(request.getMaxAmountPerDay());
        accountTxnLimitConfig.setMaxAmountPerMonth(request.getMaxAmountPerMonth());
        accountTxnLimitConfig.setApplyOn(request.getApplyOn());
        MfsTxnType mfsTxnType = mfsTxnTypeService.findByName(request.getTxnType());
        TransferType transferType = transferTypeServiceLocal.load(mfsTxnType.getCoreTxnTypeId(), TransferType.Relationships.FROM, TransferType.Relationships.TO);
        accountTxnLimitConfig.setMfsTypeName(mfsTxnType.getName());
        accountTxnLimitConfig.setMfsTypeDescription(StringUtils.isBlank(request.getMfsTypeDescription()) ? mfsTxnType.getDescription() : request.getMfsTypeDescription());
        accountTxnLimitConfig.setTransferType(transferType);
        accountTxnLimitConfig.setFromAcType(transferType.getFrom().getName());
        accountTxnLimitConfig.setToAcType(transferType.getTo().getName());
        accountTxnLimitConfig.setEnable(request.isEnable());
        MemberAccount account = mfsAccountService.getWalletAccount(request.getWalletNo());
        accountTxnLimitConfig.setAccount(account);
        if (StringUtils.isNotBlank(request.getGenericAccountLimit())) {
            MfsAccountGenericLimitConfig accountGnericLimitConfig = txnLimitService.loadAccountGenericLimitConfigByNameAndEnabled(account, request.getGenericAccountLimit(), true);
            accountTxnLimitConfig.setAccountGenericLimit(accountGnericLimitConfig);
        }
        return txnLimitService.saveMfsAccountTxnLimitConfig(accountTxnLimitConfig);
    }

    @RequestMapping(value = "/account/config", method = RequestMethod.PUT)
    @ResponseBody
    public AccountTxnLimitResponse updateAccountTxnLimitConfig(@Validated @RequestBody UpdateAccountTxnLimitRequest accountTxnLimitConfig) {
        return txnLimitService.updateMfsAccountTxnLimitConfig(accountTxnLimitConfig);
    }

    @RequestMapping(value = "/account/config/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteAccountTxnLimitConfig(@PathVariable Long id) {
        return txnLimitService.deleteMfsAccountTxnLimitConfig(id);
    }

    
    @RequestMapping(value = "/account/config/generic/{id}", method = RequestMethod.GET)
    @ResponseBody
    public MfsAccountGenericLimitConfig loadAccountGenericLimitConfig(@PathVariable Long id) {
        return txnLimitService.loadAccountGenericLimitConfig(id);
    }

    @RequestMapping(value = "/account/{walletNo}/config/generic", method = RequestMethod.GET)
    @ResponseBody
    public List<MfsAccountGenericLimitConfig> loadAllAccountGenericLimitConfigs(@PathVariable String walletNo) {
        MemberAccount account = mfsAccountService.getWalletAccount(walletNo);
        return txnLimitService.loadAllMfsAccountGenericLimitConfigsByAccount(account);
    }

    @RequestMapping(value = "/account/{walletNo}/config/generic/active", method = RequestMethod.GET)
    @ResponseBody
    public List<MfsAccountGenericLimitConfig> loadAllActiveAccountGenericLimitConfigs(@PathVariable String walletNo) {
        MemberAccount account = mfsAccountService.getWalletAccount(walletNo);
        return txnLimitService.loadAllMfsAccountGenericLimitConfigsByAccountAndStatus(account, true);
    }

    @RequestMapping(value = "/account/{walletNo}/config/generic/inactive", method = RequestMethod.GET)
    @ResponseBody
    public List<MfsAccountGenericLimitConfig> loadAllInactiveAccountGenericLimitConfigs(@PathVariable String walletNo) {
        MemberAccount account = mfsAccountService.getWalletAccount(walletNo);
        return txnLimitService.loadAllMfsAccountGenericLimitConfigsByAccountAndStatus(account, false);
    }

    @RequestMapping(value = "/account/{walletNo}/config/generic", method = RequestMethod.POST)
    @ResponseBody
    public AccountGenericLimitResponse saveAccountGenericLimitConfig(@Validated @RequestBody MfsAccountGenericLimitConfig request) {
        return txnLimitService.saveMfsAccountGenericLimitConfig(request);
    }

    @RequestMapping(value = "/account/{walletNo}/config/generic", method = RequestMethod.PUT)
    @ResponseBody
    public AccountGenericLimitResponse updateAccountGenericLimitConfig(@Validated @RequestBody UpdateAccountGenericLimitRequest request) {
        return txnLimitService.updateMfsAccountGenericLimitConfig(request);
    }

    @RequestMapping(value = "/account/{walletNo}/config/all", method = RequestMethod.GET)
    @ResponseBody
    public WalletLimitResponse loadAccountLimitConfigs(@PathVariable String walletNo) {
        MemberAccount account = mfsAccountService.getWalletAccount(walletNo);
        WalletLimitResponse response = txnLimitService.loadAccountLimitsByAccount(account);
        response.setWalletNo(walletNo);
        return response;
    }

    @RequestMapping(value = "/account/{walletNo}/config/all", method = RequestMethod.POST)
    @ResponseBody
    public WalletLimitResponse saveAccountLimitConfigs(@PathVariable String walletNo, @Validated @RequestBody TxnLimitExchangeDTO accountLimits) {
        MemberAccount account = mfsAccountService.getWalletAccount(walletNo);
        TransferTypeQuery query = new TransferTypeQuery();
        query.setFromOrToAccountType(account.getType());
        query.fetch(TransferType.Relationships.FROM, TransferType.Relationships.TO);
        Map<Long, TransferType> transferTypes = transferTypeServiceLocal.search(query).stream().collect(Collectors.toMap(TransferType::getId, Function.identity()));
        return txnLimitService.saveAccountLimitsByAccount(account, accountLimits.getLimits(), transferTypes);
    }

    @RequestMapping(value = "/account/type/{accountTypeId}/category/{categoryId}/config/all", method = RequestMethod.GET)
    @ResponseBody
    public WalletLimitResponse loadGroupLimitConfigs(@PathVariable long accountTypeId, @PathVariable long categoryId) {
        MemberAccountType accountType = (MemberAccountType) accountTypeServiceLocal.load(accountTypeId);
        MemberGroup group = groupServiceLocal.load(categoryId);
        WalletLimitResponse response = txnLimitService.loadLimitsByGroup(group, accountType);
        return response;
    }

    @RequestMapping(value = "/account/type/{accountTypeId}/category/{categoryId}/config/all", method = RequestMethod.POST)
    @ResponseBody
    public TxnLimitExchangeDTO saveAccountCaategoryTxnLimits(@Validated @RequestBody TxnLimitExchangeDTO categoryLimits, @PathVariable long accountTypeId, @PathVariable long categoryId) {
        MemberAccountType accountType = (MemberAccountType) accountTypeServiceLocal.load(accountTypeId);
        MemberGroup group = groupServiceLocal.load(categoryId, MemberGroup.Relationships.GROUP_TXN_LIMIT_CONFIGS);
        TransferTypeQuery query = new TransferTypeQuery();
        query.setFromOrToAccountType(accountType);
        query.fetch(TransferType.Relationships.FROM, TransferType.Relationships.TO);
        Map<Long, TransferType> transferTypes = transferTypeServiceLocal.search(query).stream().collect(Collectors.toMap(TransferType::getId, Function.identity()));
        TxnLimitExchangeDTO exchangeDTO = new TxnLimitExchangeDTO();
        exchangeDTO.setLimits(txnLimitService.saveGroupLimits(group, accountType, categoryLimits.getLimits(), transferTypes));
        return exchangeDTO;
    }

    @RequestMapping(value = "/account/type/{accountTypeId}/category/{categoryId}/credit-limit", method = RequestMethod.GET)
    @ResponseBody
    public AccountCategoryCreditLimitDTO getAccountCategoryCreditLimit(@PathVariable long accountTypeId, @PathVariable long categoryId) {
        AccountCategoryCreditLimitDTO categoryCreditLimit = new AccountCategoryCreditLimitDTO();
        MemberGroupAccountSettings groupAccountSetting = groupServiceLocal.loadAccountSettings(categoryId, accountTypeId, MemberGroupAccountSettings.Relationships.GROUP, MemberGroupAccountSettings.Relationships.ACCOUNT_TYPE);
        categoryCreditLimit.setAccountCategoryId(groupAccountSetting.getGroup().getId());
        categoryCreditLimit.setAccountCategoryName(groupAccountSetting.getGroup().getName());
        categoryCreditLimit.setAccountTypeId(groupAccountSetting.getAccountType().getId());
        categoryCreditLimit.setAccountTypeName(groupAccountSetting.getAccountType().getName());
        categoryCreditLimit.setLowerCreditLimt(groupAccountSetting.getDefaultCreditLimit());
        categoryCreditLimit.setUpperCreditLimit(groupAccountSetting.getDefaultUpperCreditLimit());
        return categoryCreditLimit;
    }

    @RequestMapping(value = "/account/type/{accountTypeId}/category/{categoryId}/credit-limit", method = RequestMethod.POST)
    @ResponseBody
    public AccountCategoryCreditLimitDTO saveAccountCategoryCreditLimit(@Validated @RequestBody AccountCategoryCreditLimitRequestDTO cetegoryCreditLimit, @PathVariable long accountTypeId, @PathVariable long categoryId) {
        AccountCategoryCreditLimitDTO categoryCreditLimit = new AccountCategoryCreditLimitDTO();
        MemberGroupAccountSettings groupAccountSetting = groupServiceLocal.loadAccountSettings(categoryId, accountTypeId, MemberGroupAccountSettings.Relationships.GROUP, MemberGroupAccountSettings.Relationships.ACCOUNT_TYPE);
        groupAccountSetting.setDefaultCreditLimit(cetegoryCreditLimit.getLowerCreditLimit());
        groupAccountSetting.setDefaultUpperCreditLimit(cetegoryCreditLimit.getUpperCreditLimit());
        MemberGroupAccountSettings updatedGroupAccountSetting = groupServiceLocal.updateAccountSettingsByRest(groupAccountSetting, cetegoryCreditLimit.isUpdateAccountLimits());
        categoryCreditLimit.setAccountCategoryId(updatedGroupAccountSetting.getGroup().getId());
        categoryCreditLimit.setAccountCategoryName(updatedGroupAccountSetting.getGroup().getName());
        categoryCreditLimit.setAccountTypeId(updatedGroupAccountSetting.getAccountType().getId());
        categoryCreditLimit.setAccountTypeName(updatedGroupAccountSetting.getAccountType().getName());
        categoryCreditLimit.setLowerCreditLimt(updatedGroupAccountSetting.getDefaultCreditLimit());
        categoryCreditLimit.setUpperCreditLimit(updatedGroupAccountSetting.getDefaultUpperCreditLimit());
        return categoryCreditLimit;
    }

    @RequestMapping(value = "/account/{walletNo}/credit-limit", method = RequestMethod.GET)
    @ResponseBody
    public AccountCreditLimitDTO getAccountCreditLimit(@PathVariable String walletNo) {
        MemberAccount account = mfsAccountService.getWalletAccount(walletNo);
        AccountCreditLimitDTO accountCreditLimit = new AccountCreditLimitDTO();
        accountCreditLimit.setWalletNo(account.getOwnerName());
        accountCreditLimit.setLowerCreditLimit(account.getCreditLimit());
        accountCreditLimit.setUpperCreditLimit(account.getUpperCreditLimit());
        return accountCreditLimit;
    }

    @RequestMapping(value = "/account/{walletNo}/credit-limit", method = RequestMethod.POST)
    @ResponseBody
    public AccountCreditLimitDTO saveAccountCategoryCreditLimit(@Validated @RequestBody AccountCreditLimitDTO accountCreditLimit, @PathVariable String walletNo) {
        MemberAccount account = mfsAccountService.getWalletAccount(walletNo);
        MemberAccount updatedAccount = mfsAccountService.setAccountLimit(account, accountCreditLimit);
        accountCreditLimit.setWalletNo(walletNo);
        accountCreditLimit.setLowerCreditLimit(updatedAccount.getCreditLimit());
        accountCreditLimit.setUpperCreditLimit(updatedAccount.getUpperCreditLimit());
        return accountCreditLimit;
    }
}

