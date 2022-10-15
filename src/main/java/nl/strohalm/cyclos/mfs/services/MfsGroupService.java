package nl.strohalm.cyclos.mfs.services;

import nl.strohalm.cyclos.mfs.dao.MfsGroupConfigDao;
import nl.strohalm.cyclos.mfs.entities.MfsGroupConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MfsGroupService {
  @Autowired
  MfsGroupConfigDao mfsGroupConfigDao;

  public MfsGroupConfig create(MfsGroupConfig mfsTxnType) {
    return mfsGroupConfigDao.insert(mfsTxnType);
  }

  public MfsGroupConfig update(MfsGroupConfig mfsTxnType) {
    return mfsGroupConfigDao.update(mfsTxnType);
  }

  public int delete(Long id) {
    return mfsGroupConfigDao.delete(id);
  }

  public MfsGroupConfig get(Long id) {
    return mfsGroupConfigDao.load(id);
  }

  public List<MfsGroupConfig> get() {
    return mfsGroupConfigDao.get();
  }

  public MfsGroupConfig findByName(String name) {
    return mfsGroupConfigDao.findByName(name);
  }
}
