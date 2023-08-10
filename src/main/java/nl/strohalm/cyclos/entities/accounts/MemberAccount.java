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
package nl.strohalm.cyclos.entities.accounts;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;

import nl.strohalm.cyclos.entities.Relationship;
import nl.strohalm.cyclos.entities.accounts.AccountType.Nature;
import nl.strohalm.cyclos.entities.members.Member;
import nl.strohalm.cyclos.mfs.entities.MfsAccountTxnLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsAccountTypeGroup;
import nl.strohalm.cyclos.mfs.entities.MfsTxnLimitConfig;
import nl.strohalm.cyclos.utils.FormatObject;
import nl.strohalm.cyclos.utils.StringValuedEnum;

/**
 * An account owned by a member
 * @author luis
 */
public class MemberAccount extends Account {
    public static enum Action implements StringValuedEnum {
        ACTIVATE("A"), REMOVE("R");
        private final String value;

        private Action(final String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }

    }

    public static enum Relationships implements Relationship {
        MEMBER("member"), ACCOUNT_LIMIT_CONFIGS("accountTxnLimitConfigs");
        private final String name;

        private Relationships(final String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    public static enum Status implements StringValuedEnum {
        ACTIVE("ACTIVE"),
        PENDING("PENDING"),
        SUSPENDED("SUSPENDED"),
        CLOSED("CLOSED"),
        INACTIVE("INACTIVE"),
        REMOVED("REMOVED");
        private final String value;

        private Status(final String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    private static final long serialVersionUID = 2295911628180790052L;

    private Member            member;
    private Status            status           = Status.ACTIVE;
    private Action            action;
    private Calendar          lastLowUnitsSent;
    private Collection<MfsAccountTxnLimitConfig> accountTxnLimitConfigs;

    public MemberAccount() {
    }

    public MemberAccount(final Member owner) {
        setMember(owner);
    }

    public Action getAction() {
        return action;
    }

    @Override
    public BigDecimal getCreditLimit() {
        final BigDecimal fromSuper = super.getCreditLimit();
        // Ensure that a member account will NEVER be unlimited
        if (fromSuper == null) {
            return BigDecimal.ZERO;
        }
        return fromSuper;
    }

    public Calendar getLastLowUnitsSent() {
        return lastLowUnitsSent;
    }

    public Member getMember() {
        return member;
    }

    @Override
    public Nature getNature() {
        return Nature.MEMBER;
    }

    @Override
    public Member getOwner() {
        return getMember();
    }

    public Status getStatus() {
        return status;
    }

    public void setAction(final Action action) {
        this.action = action;
    }

    public void setLastLowUnitsSent(final Calendar lastLowUnitsSent) {
        this.lastLowUnitsSent = lastLowUnitsSent;
    }

    public void setMember(final Member owner) {
        member = owner;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    
    public Collection<MfsAccountTxnLimitConfig> getAccountTxnLimitConfigs() {
        return accountTxnLimitConfigs;
    }

    public void setAccountTxnLimitConfigs(Collection<MfsAccountTxnLimitConfig> accountTxnLimitConfigs) {
        this.accountTxnLimitConfigs = accountTxnLimitConfigs;
    }

    public void addAccountTxnLimitConfig(MfsAccountTxnLimitConfig child){
        if(child!=null){
          this.accountTxnLimitConfigs.add(child);
          child.setAccount(this);   
        }
   }

   public void removeAccountTxnLimitConfig(MfsAccountTxnLimitConfig child){
        if(child!=null){
          this.accountTxnLimitConfigs.remove(child);
          child.setAccount(null);   
        }
   }

   public void updateAccountTxnLimitConfigs(Collection<MfsAccountTxnLimitConfig> children) {
       for (MfsAccountTxnLimitConfig c: accountTxnLimitConfigs) {
           if(!children.contains(c)) {
               this.removeAccountTxnLimitConfig(c);
           }
       }
       children.forEach(this::addAccountTxnLimitConfig);
   }
   
	@Override
    public String toString() {
        return getId() + ", type: " + FormatObject.formatObject(getType()) + ", member: " + FormatObject.formatObject(getMember());
    }

}
