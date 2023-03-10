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
package nl.strohalm.cyclos.services.accounts.guarantees;

import java.math.BigDecimal;

import nl.strohalm.cyclos.entities.accounts.guarantees.GuaranteeType.FeeType;
import nl.strohalm.cyclos.utils.DataObject;

public class GuaranteeFeeVO extends DataObject {

    private static final long serialVersionUID = -5216565293055600738L;

    private FeeType           type;
    private BigDecimal        fee;

    public BigDecimal getFee() {
        return fee;
    }

    public FeeType getType() {
        return type;
    }

    public void setFee(final BigDecimal fee) {
        this.fee = fee;
    }

    public void setType(final FeeType feeType) {
        type = feeType;
    }
}
