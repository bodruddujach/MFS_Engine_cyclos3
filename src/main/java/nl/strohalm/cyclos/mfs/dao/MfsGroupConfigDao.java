package nl.strohalm.cyclos.mfs.dao;

import nl.strohalm.cyclos.dao.BaseDAO;
import nl.strohalm.cyclos.dao.DeletableDAO;
import nl.strohalm.cyclos.dao.InsertableDAO;
import nl.strohalm.cyclos.dao.UpdatableDAO;
import nl.strohalm.cyclos.mfs.entities.MfsGroupConfig;

import java.util.List;


public interface MfsGroupConfigDao extends BaseDAO<MfsGroupConfig>, InsertableDAO<MfsGroupConfig>, UpdatableDAO<MfsGroupConfig>, DeletableDAO<MfsGroupConfig> {
  List<MfsGroupConfig> get();
  MfsGroupConfig findByName(String name);
}
