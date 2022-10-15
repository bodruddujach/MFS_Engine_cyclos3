package nl.strohalm.cyclos.mfs.controllers;

import nl.strohalm.cyclos.mfs.entities.TxnLimitConfig;
import nl.strohalm.cyclos.mfs.services.TxnLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("v1/api/limit")
public class TxnLimitController {

    @Autowired
    TxnLimitService txnLimitService;

    @RequestMapping(value = "/config/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TxnLimitConfig loadTxnLimitConfig(@PathVariable Long id) {
        return txnLimitService.loadTxnLimitConfig(id);
    }

    @RequestMapping(value = "/config", method = RequestMethod.POST)
    @ResponseBody
    public TxnLimitConfig saveTxnLimitConfig(@Validated @RequestBody TxnLimitConfig txnLimitConfig) {
        return txnLimitService.saveTxnLimitConfig(txnLimitConfig);
    }

    @RequestMapping(value = "/config", method = RequestMethod.PUT)
    @ResponseBody
    public TxnLimitConfig updateTxnLimitConfig(@Validated @RequestBody TxnLimitConfig txnLimitConfig) {
        return txnLimitService.updateTxnLimitConfig(txnLimitConfig);
    }

    @RequestMapping(value = "/config/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteTxnLimitConfig(@PathVariable Long id) {
        return txnLimitService.deleteTxnLimitConfig(id);
    }

    // todo config list

    // todo customer limit list


}

