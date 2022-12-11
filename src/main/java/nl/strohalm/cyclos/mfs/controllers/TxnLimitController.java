package nl.strohalm.cyclos.mfs.controllers;

import nl.strohalm.cyclos.entities.accounts.transactions.TransferType;
import nl.strohalm.cyclos.mfs.entities.MfsTxnLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType;
import nl.strohalm.cyclos.mfs.entities.TxnLimitConfig;
import nl.strohalm.cyclos.mfs.models.transactions.TxnLimitRequest;
import nl.strohalm.cyclos.mfs.models.transactions.TxnLimitResponse;
import nl.strohalm.cyclos.mfs.models.transactions.UpdateTxnLimitRequest;
import nl.strohalm.cyclos.mfs.services.MfsTxnTypeService;
import nl.strohalm.cyclos.mfs.services.TxnLimitService;
import nl.strohalm.cyclos.services.transfertypes.TransferTypeServiceLocal;

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
        TransferType transferType = transferTypeServiceLocal.load(mfsTxnType.getCoreTxnTypeId());
        txnLimitConfig.setTransferType(transferType);
        txnLimitConfig.setEnable(request.isEnable());
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

    // todo config list

    // todo customer limit list


}

