package nl.strohalm.cyclos.mfs.controllers;


import nl.strohalm.cyclos.mfs.entities.MFSMerchant;
import nl.strohalm.cyclos.mfs.services.MFSMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("v1/api/merchant")
public class MerchantController {
    private static final String HEADER_JSON = "Content-type=application/json";
    @Autowired
    MFSMerchantService mfsMerchantService;
    @RequestMapping(value = "/mfs-merchant/{id}", method = RequestMethod.GET)
    @ResponseBody
    public MFSMerchant loadMFSMerchant(@PathVariable Long id) {
        return mfsMerchantService.loadMFSMerchant(id);
    }
    @RequestMapping(value = "/mfs-merchant", method = RequestMethod.POST)
    @ResponseBody
    public MFSMerchant saveMFSMerchant(@Validated @RequestBody MFSMerchant resMFSMerchant) {
        return mfsMerchantService.saveMFSMerchant(resMFSMerchant);
    }
    @RequestMapping(value = "/mfs-merchant", method = RequestMethod.PUT)
    @ResponseBody
    public MFSMerchant updateMFSMerchant(@Validated @RequestBody MFSMerchant resMFSMerchant) {
        return mfsMerchantService.updateMFSMerchant(resMFSMerchant);
    }
    @RequestMapping(value = "/mfs-merchant/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteMFSMerchant(@PathVariable Long id) {
       return mfsMerchantService.deleteMFSMerchant(id);
    }

}
