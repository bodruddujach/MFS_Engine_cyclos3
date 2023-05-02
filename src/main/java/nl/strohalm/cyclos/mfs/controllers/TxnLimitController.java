package nl.strohalm.cyclos.mfs.controllers;

import nl.strohalm.cyclos.entities.accounts.transactions.TransferType;
import nl.strohalm.cyclos.mfs.entities.MfsGenericLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsTxnLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType;
import nl.strohalm.cyclos.mfs.models.transactions.GenericLimitResponse;
import nl.strohalm.cyclos.mfs.models.transactions.TxnLimitRequest;
import nl.strohalm.cyclos.mfs.models.transactions.TxnLimitResponse;
import nl.strohalm.cyclos.mfs.models.transactions.UpdateGenericLimitRequest;
import nl.strohalm.cyclos.mfs.models.transactions.UpdateTxnLimitRequest;
import nl.strohalm.cyclos.mfs.services.MfsTxnTypeService;
import nl.strohalm.cyclos.mfs.services.TxnLimitService;
import nl.strohalm.cyclos.services.transfertypes.TransferTypeServiceLocal;

import java.util.List;

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
    TransferTypeServiceLocal transferTypeServiceLocal;

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


}

