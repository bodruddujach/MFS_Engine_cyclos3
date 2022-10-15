package nl.strohalm.cyclos.mfs.services;

import nl.strohalm.cyclos.mfs.dao.MfsTxnTypeDao;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MfsTxnTypeService {
  @Autowired
  private MfsTxnTypeDao mfsTxnTypeDao;

  public MfsTxnType create(MfsTxnType mfsTxnType) {
    return mfsTxnTypeDao.insert(mfsTxnType);
  }

  public MfsTxnType update(MfsTxnType mfsTxnType) {
    return mfsTxnTypeDao.update(mfsTxnType);
  }

  public int delete(Long id) {
    return mfsTxnTypeDao.delete(id);
  }

  public MfsTxnType get(Long id) {
    return mfsTxnTypeDao.load(id);
  }

  public List<MfsTxnType> get() {
    return mfsTxnTypeDao.get();
  }

  public MfsTxnType findByName(String name){
     return mfsTxnTypeDao.findByName(name);
  }

  public MfsTxnType findByCoreId(Long txnTypeId){
    return mfsTxnTypeDao.findByTxnId(txnTypeId);
  }
}
