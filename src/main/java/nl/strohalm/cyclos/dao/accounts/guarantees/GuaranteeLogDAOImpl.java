/*
    This file is part of Cyclos (www.cyclos.org).
    A project of the Social Trade Organisation (www.socialtrade.org).

    Cyclos is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    Cyclos is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Cyclos; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 */
package nl.strohalm.cyclos.dao.accounts.guarantees;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.strohalm.cyclos.dao.BaseDAOImpl;
import nl.strohalm.cyclos.entities.accounts.guarantees.GuaranteeLog;
import nl.strohalm.cyclos.entities.accounts.guarantees.GuaranteeLogQuery;
import nl.strohalm.cyclos.entities.exceptions.DaoException;
import nl.strohalm.cyclos.utils.hibernate.HibernateHelper;

public class GuaranteeLogDAOImpl extends BaseDAOImpl<GuaranteeLog> implements GuaranteeLogDAO {

    public GuaranteeLogDAOImpl() {
        super(GuaranteeLog.class);
    }

    public List<GuaranteeLog> search(final GuaranteeLogQuery queryParameters) throws DaoException {
        final Map<String, Object> namedParameters = new HashMap<String, Object>();
        final StringBuilder hql = HibernateHelper.getInitialQuery(getEntityType(), "gl", queryParameters.getFetch());

        if (queryParameters.hasStatus()) {
            HibernateHelper.addInParameterToQuery(hql, namedParameters, "gl.status", queryParameters.getStatusCollection());
        }
        return list(queryParameters, hql.toString(), null);
    }
}
