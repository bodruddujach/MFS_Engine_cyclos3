package nl.strohalm.cyclos.mfs.dao;

import java.util.List;
import java.util.Set;

import nl.strohalm.cyclos.dao.BaseDAO;
import nl.strohalm.cyclos.dao.DeletableDAO;
import nl.strohalm.cyclos.dao.InsertableDAO;
import nl.strohalm.cyclos.dao.UpdatableDAO;
import nl.strohalm.cyclos.entities.accounts.Account;
import nl.strohalm.cyclos.entities.accounts.transactions.TransferType;
import nl.strohalm.cyclos.mfs.entities.MfsAccountTxnLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsAccountTxnLimitConfig.LimitSubject;

public interface MfsAccountTxnLimitConfigDAO  extends BaseDAO<MfsAccountTxnLimitConfig>, InsertableDAO<MfsAccountTxnLimitConfig>, UpdatableDAO<MfsAccountTxnLimitConfig>, DeletableDAO<MfsAccountTxnLimitConfig> {

	List<MfsAccountTxnLimitConfig> getAllMfsAccountTxnLimitConfigs();

	List<MfsAccountTxnLimitConfig> getAllMfsAccountTxnLimitConfigsByAccount(Account account);

	List<MfsAccountTxnLimitConfig> getMfsAccountTxnLimitConfigsByStatus(boolean enabled);

	List<MfsAccountTxnLimitConfig> getMfsAccountTxnLimitConfigsByStatusAndAccount(boolean enabled, Account account);

	List<MfsAccountTxnLimitConfig> loadMfsAccountTxnLimitConfigByTransferTypeAndApplyOn(TransferType transferType, LimitSubject applyOn);

	List<MfsAccountTxnLimitConfig> getMfsAccountTxnLimitConfigsByStatusAndAccountType(boolean enabled, String accountType);

	List<MfsAccountTxnLimitConfig> loadMfsAccountTxnLimitConfigByTransferTypeAndAccountAndApplyOn(TransferType transferType, Account account, LimitSubject applyOn);

	List<MfsAccountTxnLimitConfig> getMfsAccountTxnLimitConfigsByStatusAndTransferTypeAndAccountsIn(boolean enabled, TransferType transferType, Set<Account> accounts);
}
