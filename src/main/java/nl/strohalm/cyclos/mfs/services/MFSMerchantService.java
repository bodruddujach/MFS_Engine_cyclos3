package nl.strohalm.cyclos.mfs.services;


import nl.strohalm.cyclos.mfs.dao.MFSMerchantDAO;
import nl.strohalm.cyclos.mfs.entities.MFSMerchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MFSMerchantService {

    @Autowired
    private MFSMerchantDAO mfsMerchantDAO;
    @Transactional
    public MFSMerchant saveMFSMerchant(MFSMerchant mFSMerchant) {
        return mfsMerchantDAO.insert(mFSMerchant);
    }

    @Transactional
    public MFSMerchant updateMFSMerchant(MFSMerchant mFSMerchant) {
        return mfsMerchantDAO.update(mFSMerchant);
    }

    @Transactional
    public int deleteMFSMerchant(Long mFSMerchantId) {
        return mfsMerchantDAO.delete(mFSMerchantId);
    }

    @Transactional
    public MFSMerchant loadMFSMerchant(Long mFSMerchantId) {
        return mfsMerchantDAO.load(mFSMerchantId);
    }
}
