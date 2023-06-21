package nl.strohalm.cyclos.mfs.controllers;


import nl.strohalm.cyclos.mfs.entities.MFSLedgerAccount;
import nl.strohalm.cyclos.mfs.models.accounts.BalanceResponse;
import nl.strohalm.cyclos.mfs.models.ledgers.LedgerTxnRequest;
import nl.strohalm.cyclos.mfs.models.ledgers.LedgerTxnResponse;
import nl.strohalm.cyclos.mfs.models.ledgers.LedgerTxnSummaryRequest;
import nl.strohalm.cyclos.mfs.models.ledgers.LedgerTxnSummaryResponse;
import nl.strohalm.cyclos.mfs.services.LedgerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("v1/api/ledger")
public class LedgerController {

  @Autowired
  LedgerService ledgerService;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public MFSLedgerAccount loadMFSLedgerAccount(@PathVariable Long id) {
    return ledgerService.loadMFSLedgerAccount(id);
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  public MFSLedgerAccount saveMFSLedgerAccount(@Validated @RequestBody MFSLedgerAccount resMFSLedgerAccount) {
    return ledgerService.saveMFSLedgerAccount(resMFSLedgerAccount);
  }

  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  public MFSLedgerAccount updateMFSLedgerAccount(@Validated @RequestBody MFSLedgerAccount resMFSLedgerAccount) {
    return ledgerService.updateMFSLedgerAccount(resMFSLedgerAccount);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public int deleteMFSLedgerAccount(@PathVariable Long id) {
    return ledgerService.deleteMFSLedgerAccount(id);
  }

  @RequestMapping(value = "/code/{ledgerCode}", method = RequestMethod.GET)
  @ResponseBody
  public MFSLedgerAccount ledgerCode(@PathVariable("ledgerCode") String ledgerCode) {
    return ledgerService.getLedgerByCode(ledgerCode);
  }

  @RequestMapping(value = "/search/{ledgerCode}", method = RequestMethod.GET)
  @ResponseBody
  public List<MFSLedgerAccount> getAllLedgerAc(@PathVariable("ledgerCode") String ledgerCode) {
    return ledgerService.searchAccount(ledgerCode);
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public List<MFSLedgerAccount> getAllLedgerAc() {
    return ledgerService.getAllLedgerAc();
  }

  @RequestMapping(value = "/balance/at/{ledgerCode}/{balanceDate}", method = RequestMethod.GET)
  @ResponseBody
  public BalanceResponse ledgerBalanceAt(@PathVariable("ledgerCode") String ledgerCode, @PathVariable("balanceDate") Calendar balanceDate) {
    return ledgerService.ledgerBalance(ledgerCode, balanceDate);
  }

  @RequestMapping(value = "/balance/{ledgerCode}", method = RequestMethod.GET)
  @ResponseBody
  public BalanceResponse currentLedgerBalance(@PathVariable("ledgerCode") String ledgerCode) {
    return ledgerService.ledgerBalance(ledgerCode, null);
  }

  @RequestMapping(value = "/txn/summary", method = RequestMethod.POST)
  @ResponseBody
  public LedgerTxnSummaryResponse ledgerTransactionSummary(@Validated @RequestBody LedgerTxnSummaryRequest request) {
    return ledgerService.getLedgerTransactionSummary(request);
  }

  @RequestMapping(value = "/txn", method = RequestMethod.POST)
  @ResponseBody
  public LedgerTxnResponse doLedgerTxn(@Validated @RequestBody LedgerTxnRequest ledgerTxnRequest) {
    return null;
  }

}
