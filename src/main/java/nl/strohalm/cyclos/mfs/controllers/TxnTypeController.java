package nl.strohalm.cyclos.mfs.controllers;

import nl.strohalm.cyclos.entities.accounts.transactions.TransferType;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType.AccountTypeNature;
import nl.strohalm.cyclos.mfs.exceptions.ErrorConstants;
import nl.strohalm.cyclos.mfs.exceptions.MFSCommonException;
import nl.strohalm.cyclos.mfs.services.MfsTxnTypeService;
import nl.strohalm.cyclos.services.transfertypes.TransferTypeServiceLocal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("v1/api/txn-type")
public class TxnTypeController {

  @Autowired
  MfsTxnTypeService mfsTxnTypeService;

  @Autowired
  TransferTypeServiceLocal transferTypeServiceLocal;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public MfsTxnType find(@PathVariable Long id) {
    return mfsTxnTypeService.get(id);
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public List<MfsTxnType> get() {
    return mfsTxnTypeService.get();
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  public MfsTxnType create(@Valid @RequestBody MfsTxnType mfsTxnType) {
    TransferType coreType = transferTypeServiceLocal.load(mfsTxnType.getCoreTxnTypeId());
    if (coreType == null)
        throw new MFSCommonException(ErrorConstants.TXN_TYPE_NOT_FOUND, ErrorConstants.ERROR_MAP.get(ErrorConstants.TXN_TYPE_NOT_FOUND), HttpStatus.BAD_REQUEST);
    mfsTxnType.setFromTypeId(coreType.getFrom().getId());
    mfsTxnType.setFromTypeName(coreType.getFrom().getName());
    mfsTxnType.setFromTypeNature(AccountTypeNature.valueOf(coreType.getFrom().getNature().name()));
    mfsTxnType.setToTypeId(coreType.getTo().getId());
    mfsTxnType.setToTypeName(coreType.getTo().getName());
    mfsTxnType.setToTypeNature(AccountTypeNature.valueOf(coreType.getFrom().getNature().name()));
    return mfsTxnTypeService.create(mfsTxnType);
  }

  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  public MfsTxnType update(@Validated @RequestBody MfsTxnType mfsTxnType) {
    TransferType coreType = transferTypeServiceLocal.load(mfsTxnType.getCoreTxnTypeId());
    if (coreType == null)
        throw new MFSCommonException(ErrorConstants.TXN_TYPE_NOT_FOUND, ErrorConstants.ERROR_MAP.get(ErrorConstants.TXN_TYPE_NOT_FOUND), HttpStatus.BAD_REQUEST);
    mfsTxnType.setFromTypeId(coreType.getFrom().getId());
    mfsTxnType.setFromTypeName(coreType.getFrom().getName());
    mfsTxnType.setFromTypeNature(AccountTypeNature.valueOf(coreType.getFrom().getNature().name()));
    mfsTxnType.setToTypeId(coreType.getTo().getId());
    mfsTxnType.setToTypeName(coreType.getTo().getName());
    mfsTxnType.setToTypeNature(AccountTypeNature.valueOf(coreType.getTo().getNature().name()));
    return mfsTxnTypeService.update(mfsTxnType);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public int delete(@PathVariable Long id) {
    return mfsTxnTypeService.delete(id);
  }


}
