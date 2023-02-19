package nl.strohalm.cyclos.mfs.controllers;

import nl.strohalm.cyclos.entities.accounts.transactions.Transfer;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType.TxnTypeTag;
import nl.strohalm.cyclos.mfs.models.accounts.BalanceResponse;
import nl.strohalm.cyclos.mfs.models.accounts.WalletStatementResp;
import nl.strohalm.cyclos.mfs.models.transactions.BulkTxnRequest;
import nl.strohalm.cyclos.mfs.models.transactions.TxnRequest;
import nl.strohalm.cyclos.mfs.models.transactions.TxnResponse;
import nl.strohalm.cyclos.mfs.models.transactions.TxnReversalRequest;
import nl.strohalm.cyclos.mfs.models.transactions.TxnReversalResponse;
import nl.strohalm.cyclos.mfs.services.MfsAccountService;
import nl.strohalm.cyclos.mfs.services.TransactionService;
import nl.strohalm.cyclos.webservices.rest.BaseRestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/v1/api/txn")
public class TxnController extends BaseRestController {
  public static final Logger logger = LogManager.getLogger(TxnController.class);

  @Autowired
  TransactionService transactionService;

  @RequestMapping(value = "/ping",method = RequestMethod.GET)
  @ResponseBody
  public String ping() {
    logger.info("Ping");
    return "Welcome mfs core txn controller";
  }

  @RequestMapping(method = RequestMethod.POST, headers = "Content-type=application/json")
  @ResponseBody
  public TxnResponse performTxn(@Validated @RequestBody final TxnRequest request) {
    long s = System.currentTimeMillis();
    logger.info(String.format("Txn Request From: %s | To Ac: %s | Txn Type: %s | Amount: %s",
        request.getFromAc(), request.getToAc(), request.getTxnType(), request.getAmount()));
    request.setTxnTypeTag(TxnTypeTag.MFS);
    TxnResponse result = transactionService.processTransaction(request);
    long e = System.currentTimeMillis();
    logger.info(String.format("Txn Resp: Status: %s | TxnID: %s | From: %s | To : %s | Type: %s | Amount: %s | Latency Time: %s ms",
        result.getStatus(), result.getTxnId(), request.getFromAc(), request.getToAc(),
        request.getTxnType(), request.getAmount(), (e - s)));
    return result;

  }

  @RequestMapping(value = "/estimate",method = RequestMethod.POST, headers = "Content-type=application/json")
  @ResponseBody
  public TxnResponse estimateTxn(@Validated @RequestBody final TxnRequest request) {
    long s = System.currentTimeMillis();
    logger.info(String.format("Txn Estimate Request From: %s | To Ac: %s | Txn Type: %s | Amount: %s",
      request.getFromAc(), request.getToAc(), request.getTxnType(), request.getAmount()));
    TxnResponse result = transactionService.estimate(request);
    long e = System.currentTimeMillis();
    logger.info(String.format("Txn Estimate Resp: Status: %s | TxnID: %s | From: %s | To : %s | Type: %s | Amount: %s | Latency Time: %s ms",
      result.getStatus(), result.getTxnId(), request.getFromAc(), request.getToAc(),
      request.getTxnType(), request.getAmount(), (e - s)));
    return result;

  }

  @RequestMapping(value = "/detail/{txnId}", method = RequestMethod.GET)
  @ResponseBody
  public WalletStatementResp getTxnDetail(@PathVariable String txnId) {
    return transactionService.getTxnDetail(txnId);
  }


  @RequestMapping(value = "/bulk", method = RequestMethod.POST, headers = "Content-type=application/json")
  @ResponseBody
  public List<TxnResponse> doBulkPayment(@Validated @RequestBody final BulkTxnRequest bulkTxnRequest) {
    long s = System.currentTimeMillis();
    logger.info("Bulk Txn Request, Total Payment Req: " + bulkTxnRequest.getTxnRequestList().size());
    List<TxnResponse> resultList = transactionService.processBulkTxn(bulkTxnRequest);
    long e = System.currentTimeMillis();
    logger.info("Bulk Payment Resp Time: " + (e - s) + " ms");
    return resultList;
  }

  @RequestMapping(value = "/reverse", method = RequestMethod.POST, headers = "Content-type=application/json")
  @ResponseBody
  public TxnReversalResponse doReverse(@Validated @RequestBody final TxnReversalRequest request) {
    long s = System.currentTimeMillis();
    logger.info("Txn reverse Request From: " + request.getFromAc() + "| To Ac:" + request.getToAc() + " | Txn Id:" + request.getTxnId() + "| Amount" + request.getAmount());
    TxnReversalResponse result = transactionService.processReverseTxn(request);
    long e = System.currentTimeMillis();
    logger.info(String.format("Txn reversal Resp: Status: %s, TxnId: %s, Time: %s", result.getStatus(),
        result.getTxnId(), (e - s)));
    return result;
  }

  @RequestMapping(value = "/add-money", method = RequestMethod.POST, headers = "Content-type=application/json")
  @ResponseBody
  public TxnResponse performAddMoney(@Validated @RequestBody final TxnRequest request) {
    long s = System.currentTimeMillis();
    logger.info(String.format("Txn Request From: %s | To Ac: %s | Txn Type: %s | Amount: %s",
        request.getFromAc(), request.getToAc(), request.getTxnType(), request.getAmount()));
    request.setTxnTypeTag(TxnTypeTag.I_BANKING);
    TxnResponse result = transactionService.processTransaction(request);
    long e = System.currentTimeMillis();
    logger.info(String.format("Txn Resp: Status: %s | TxnID: %s | From: %s | To : %s | Type: %s | Amount: %s | Latency Time: %s ms",
        result.getStatus(), result.getTxnId(), request.getFromAc(), request.getToAc(),
        request.getTxnType(), request.getAmount(), (e - s)));
    return result;
  }

}
