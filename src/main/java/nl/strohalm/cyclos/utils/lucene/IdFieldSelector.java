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
package nl.strohalm.cyclos.utils.lucene;

import java.io.IOException;

import org.apache.lucene.index.FieldInfo;
import org.apache.lucene.index.StoredFieldVisitor;

/**
 * A {@link FieldSelector} which loads only the "id" field
 * 
 * @author luis
 */
public class IdFieldSelector extends StoredFieldVisitor {

    private static final long            serialVersionUID = 1377258602842067268L;
    private static final IdFieldSelector INSTANCE         = new IdFieldSelector();

    public static IdFieldSelector getInstance() {
        return INSTANCE;
    }

    private IdFieldSelector() {
    }

    @Override
    public Status needsField(FieldInfo fieldInfo) throws IOException {
        return "id".equals(fieldInfo.name) ? Status.YES : Status.NO;
    }
}
