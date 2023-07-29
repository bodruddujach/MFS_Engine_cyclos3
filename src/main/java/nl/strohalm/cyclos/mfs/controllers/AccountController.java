package nl.strohalm.cyclos.mfs.controllers;

import nl.strohalm.cyclos.entities.accounts.MemberAccount;
import nl.strohalm.cyclos.mfs.models.accounts.AccountActivationRequest;
import nl.strohalm.cyclos.mfs.models.accounts.BalanceResponse;
import nl.strohalm.cyclos.mfs.models.accounts.ChangePinRequest;
import nl.strohalm.cyclos.mfs.models.accounts.CheckPinRequest;
import nl.strohalm.cyclos.mfs.models.accounts.LoginResponse;
import nl.strohalm.cyclos.mfs.models.accounts.MerchantRegRequest;
import nl.strohalm.cyclos.mfs.models.accounts.RegResponse;
import nl.strohalm.cyclos.mfs.models.accounts.RunningWalletStatementRequest;
import nl.strohalm.cyclos.mfs.models.accounts.UpdateAcStatus;
import nl.strohalm.cyclos.mfs.models.accounts.UpdateAccountRequest;
import nl.strohalm.cyclos.mfs.models.accounts.WalletInfoResponse;
import nl.strohalm.cyclos.mfs.models.accounts.WalletStatementRequest;
import nl.strohalm.cyclos.mfs.models.accounts.WalletStatementResp;
import nl.strohalm.cyclos.mfs.models.transactions.AccountLimitData;
import nl.strohalm.cyclos.mfs.models.transactions.Response;
import nl.strohalm.cyclos.mfs.services.MfsAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("v1/api/account")
public class AccountController {

  private static final String HEADER_JSON = "Content-type=application/json";

  @Autowired
  MfsAccountService accountService;

  @RequestMapping(value = "/register", method = RequestMethod.POST, headers = HEADER_JSON)
  @ResponseBody
  public RegResponse doRegister(@Validated @RequestBody MerchantRegRequest regRequest, HttpServletRequest servletRequest) {
    RegResponse regResponse = null;
    regResponse = accountService.processRegistration(regRequest);
    return regResponse;
  }

  @RequestMapping(value = "/activate", method = RequestMethod.PUT, headers = HEADER_JSON)
  @ResponseBody
  public Response activateWallet(@RequestBody AccountActivationRequest request) throws Exception {
    return accountService.activateWallet(request.getWalletNo());
  }

  @RequestMapping(value = "/status", method = RequestMethod.PUT, headers = HEADER_JSON)
  @ResponseBody
  public Response changeUserStatus(@Validated @RequestBody UpdateAcStatus acStatusUpdateRequest)
    throws Exception {
    return accountService.updateWalletStatus(acStatusUpdateRequest.getWalletNo(), MemberAccount.Status.valueOf(acStatusUpdateRequest.getWalletStatus().name()));
  }

  @RequestMapping(method = RequestMethod.PUT, headers = HEADER_JSON)
  @ResponseBody
  public Response updateWalletInformation(@Validated @RequestBody UpdateAccountRequest userRequest)
    throws Exception {
    return accountService.updateWalletInformation(userRequest);
  }

  @RequestMapping(value = "/category", method = RequestMethod.PUT, headers = HEADER_JSON)
  @ResponseBody
  public Response updateWalletAccountCategory(@Validated @RequestBody UpdateAccountRequest userRequest)
    throws Exception {
    return accountService.updateWalletInformation(userRequest);
  }

  @RequestMapping(value = "/check/pin", method = RequestMethod.POST, headers = HEADER_JSON)
  @ResponseBody
  public LoginResponse loginUser(@Validated @RequestBody CheckPinRequest checkPinRequest) {
    return accountService.loginUser(checkPinRequest);
  }

  @RequestMapping(value = "/change/pin", method = RequestMethod.PUT, headers = HEADER_JSON)
  @ResponseBody
  public Response checkPin(@Validated @RequestBody ChangePinRequest changePinRequest) {
    return accountService.changePin(changePinRequest, false);
  }


  @RequestMapping(value = "/reset/pin", method = RequestMethod.POST, headers = HEADER_JSON)
  @ResponseBody
  public Response resetPin(@Validated @RequestBody ChangePinRequest changePinRequest) {
    return accountService.resetPin(changePinRequest);
  }

  @RequestMapping(value = "/info/{walletNo}", method = RequestMethod.GET)
  @ResponseBody
  public WalletInfoResponse getWalletInfo(@PathVariable String walletNo) {
    return accountService.getWalletInfo(walletNo);
  }

  @RequestMapping(value = "/balance/{walletNo}", method = RequestMethod.GET)
  @ResponseBody
  public BalanceResponse getBalance(@PathVariable String walletNo) {
    return accountService.getCurrentBalance(walletNo, null);
  }

  @RequestMapping(value = "/balance/at/{walletNo}/{balanceDate}", method = RequestMethod.GET)
  @ResponseBody
  public BalanceResponse getBalance(@PathVariable String walletNo, @PathVariable("balanceDate") Calendar balanceDate) {
    return accountService.getCurrentBalance(walletNo, balanceDate);
  }

  @RequestMapping(value = "/statement", method = RequestMethod.POST, headers = HEADER_JSON)
  @ResponseBody
  public WalletStatementResp getWalletStatement(@Validated @RequestBody WalletStatementRequest walletStatementRequest) {
    return accountService.walletStatement(walletStatementRequest);
  }

  @RequestMapping(value = "/statement/running", method = RequestMethod.POST, headers = HEADER_JSON)
  @ResponseBody
  public WalletStatementResp getWalletRunningStatement(@Validated @RequestBody RunningWalletStatementRequest walletStatementRequest) {
    return accountService.runningWalletStatement(walletStatementRequest);
  }

  @RequestMapping(value = "/info/limit/{walletNo}", method = RequestMethod.GET)
  @ResponseBody
  public List<AccountLimitData> getWalletUsageAndLimitsInfo(@PathVariable String walletNo) {
    return accountService.getWalletUsageAndLimitsInfo(walletNo);
  }
}
