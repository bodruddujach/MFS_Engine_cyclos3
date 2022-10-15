package nl.strohalm.cyclos.mfs.controllers;

import nl.strohalm.cyclos.mfs.entities.MfsGroupConfig;

import nl.strohalm.cyclos.mfs.services.MfsGroupService;

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
@RequestMapping("v1/api/group")
public class GroupController {
  @Autowired
  MfsGroupService mfsGroupService;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public MfsGroupConfig find(@PathVariable Long id) {
    return mfsGroupService.get(id);
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public List<MfsGroupConfig> get() {
    return mfsGroupService.get();
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  public MfsGroupConfig create(@Valid @RequestBody MfsGroupConfig mfsGroupConfig) {
    return mfsGroupService.create(mfsGroupConfig);
  }

  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  public MfsGroupConfig update(@Validated @RequestBody MfsGroupConfig mfsGroupConfig) {
    return mfsGroupService.update(mfsGroupConfig);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public int delete(@PathVariable Long id) {
    return mfsGroupService.delete(id);
  }

}
