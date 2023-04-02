package nl.strohalm.cyclos.mfs.dao;

import java.util.List;

import nl.strohalm.cyclos.dao.BaseDAO;
import nl.strohalm.cyclos.dao.DeletableDAO;
import nl.strohalm.cyclos.dao.InsertableDAO;
import nl.strohalm.cyclos.dao.UpdatableDAO;
import nl.strohalm.cyclos.entities.accounts.transactions.TransferType;
import nl.strohalm.cyclos.mfs.entities.MfsTxnLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsTxnLimitConfig.LimitSubject;

public interface MfsTxnLimitConfigDAO  extends BaseDAO<MfsTxnLimitConfig>, InsertableDAO<MfsTxnLimitConfig>, UpdatableDAO<MfsTxnLimitConfig>, DeletableDAO<MfsTxnLimitConfig> {

	List<MfsTxnLimitConfig> getAllMfsTxnLimitConfigs();

	List<MfsTxnLimitConfig> getMfsTxnLimitConfigsByStatus(boolean enabled);

	List<MfsTxnLimitConfig> loadMfsTxnLimitConfigByTransferTypeAndApplyOn(TransferType transferType, LimitSubject applyOn);

}
