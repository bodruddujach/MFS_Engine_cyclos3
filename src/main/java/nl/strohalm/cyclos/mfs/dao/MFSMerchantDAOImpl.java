package nl.strohalm.cyclos.mfs.dao;

import nl.strohalm.cyclos.dao.BaseDAOImpl;
import nl.strohalm.cyclos.mfs.entities.MFSMerchant;

public class MFSMerchantDAOImpl extends BaseDAOImpl<MFSMerchant> implements MFSMerchantDAO {
    public MFSMerchantDAOImpl() {
        super(MFSMerchant.class);
    }
}
