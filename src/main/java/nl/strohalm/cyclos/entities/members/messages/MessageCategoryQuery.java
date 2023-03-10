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
package nl.strohalm.cyclos.entities.members.messages;

import java.util.Collection;

import nl.strohalm.cyclos.entities.groups.Group;
import nl.strohalm.cyclos.entities.members.Element;
import nl.strohalm.cyclos.utils.query.QueryParameters;

public class MessageCategoryQuery extends QueryParameters {

    private static final long           serialVersionUID = 6169987650793959152L;

    private Element                     fromElement;
    private Element                     toElement;
    private Collection<? extends Group> groups;

    public Element getFromElement() {
        return fromElement;
    }

    public Collection<? extends Group> getGroups() {
        return groups;
    }

    public Element getToElement() {
        return toElement;
    }

    public void setFromElement(final Element fromElement) {
        this.fromElement = fromElement;
    }

    public void setGroups(final Collection<? extends Group> groups) {
        this.groups = groups;
    }

    public void setToElement(final Element toElement) {
        this.toElement = toElement;
    }

}
