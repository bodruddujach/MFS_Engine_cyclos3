package nl.strohalm.cyclos.mfs.controllers;

import nl.strohalm.cyclos.mfs.entities.MfsTxnType;
import nl.strohalm.cyclos.mfs.services.MfsTxnTypeService;

import org.springframework.beans.factory.annotation.Autowired;
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
    return mfsTxnTypeService.create(mfsTxnType);
  }

  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  public MfsTxnType update(@Validated @RequestBody MfsTxnType mfsTxnType) {
    return mfsTxnTypeService.update(mfsTxnType);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public int delete(@PathVariable Long id) {
    return mfsTxnTypeService.delete(id);
  }


}
