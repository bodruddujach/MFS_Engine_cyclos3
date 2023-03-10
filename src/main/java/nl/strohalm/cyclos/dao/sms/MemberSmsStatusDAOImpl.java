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
package nl.strohalm.cyclos.dao.sms;

import nl.strohalm.cyclos.dao.BaseDAOImpl;
import nl.strohalm.cyclos.entities.Relationship;
import nl.strohalm.cyclos.entities.exceptions.EntityNotFoundException;
import nl.strohalm.cyclos.entities.members.Member;
import nl.strohalm.cyclos.entities.sms.MemberSmsStatus;

import org.hibernate.Query;

/**
 * Implementation for MemberSmsStatusDAO
 * 
 * @author luis
 */
public class MemberSmsStatusDAOImpl extends BaseDAOImpl<MemberSmsStatus> implements MemberSmsStatusDAO {

    public MemberSmsStatusDAOImpl() {
        super(MemberSmsStatus.class);
    }

    @Override
    public MemberSmsStatus load(final Member member, final Relationship... fetch) throws EntityNotFoundException {
        final Query query = getSession().createQuery("from MemberSmsStatus s where s.member = :member");
        query.setParameter("member", member);
        final MemberSmsStatus status = (MemberSmsStatus) query.uniqueResult();
        if (status == null) {
            throw new EntityNotFoundException(MemberSmsStatus.class);
        }
        return status;
    }

}
