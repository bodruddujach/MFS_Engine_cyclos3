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
package nl.strohalm.cyclos.services.transactions;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import nl.strohalm.cyclos.entities.access.Channel;
import nl.strohalm.cyclos.entities.accounts.AccountOwner;
import nl.strohalm.cyclos.entities.accounts.Currency;
import nl.strohalm.cyclos.entities.accounts.transactions.Ticket;
import nl.strohalm.cyclos.entities.accounts.transactions.Transfer;
import nl.strohalm.cyclos.entities.accounts.transactions.TransferType;
import nl.strohalm.cyclos.entities.customization.fields.PaymentCustomField;
import nl.strohalm.cyclos.entities.customization.fields.PaymentCustomFieldValue;
import nl.strohalm.cyclos.entities.members.Element;
import nl.strohalm.cyclos.utils.CustomFieldsContainer;
import nl.strohalm.cyclos.utils.DataObject;

/**
 * Groups parameters for a payment
 * @author luis
 */
public class DoPaymentDTO extends DataObject implements CustomFieldsContainer<PaymentCustomField, PaymentCustomFieldValue> {

    private static final long                   serialVersionUID = 7343628439444254002L;

    private AccountOwner                        from;
    private AccountOwner                        to;
    private Calendar                            date;
    private TransferType                        transferType;
    private BigDecimal                          amount;
    private Currency                            currency;
    private TransactionContext                  context;
    private String                              channel          = Channel.WEB;
    private String                              description;
    private Ticket                              ticket;
    private Element                             receiver;
    private List<ScheduledPaymentDTO>           payments;
    private Collection<PaymentCustomFieldValue> customValues;
    private String                              traceNumber;
    private boolean                             showScheduledToReceiver;
    private String note;
    private String invoiceNo;
    private String customerRefId;
    private Element                             by;
    /**
     * @see #getTraceData()
     */
    private String                              traceData;
    private String                              parentTraceData;
    private Transfer                            parent;
    private String                              mfsTransactionType;//mfs_context
    private String                              externalCustomer;//mfs_context
    private String                              systemWiseTxnId;//mfs_context
    private Calendar                            systemWiseReqTime;//mfs_context

    public BigDecimal getAmount() {
        return amount;
    }

    public String getChannel() {
        return channel;
    }

    public TransactionContext getContext() {
        return context;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public Class<PaymentCustomField> getCustomFieldClass() {
        return PaymentCustomField.class;
    }

    @Override
    public Class<PaymentCustomFieldValue> getCustomFieldValueClass() {
        return PaymentCustomFieldValue.class;
    }

    @Override
    public Collection<PaymentCustomFieldValue> getCustomValues() {
        return customValues;
    }

    public Calendar getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public AccountOwner getFrom() {
        return from;
    }

    public List<ScheduledPaymentDTO> getPayments() {
        return payments;
    }

    public Element getReceiver() {
        return receiver;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public AccountOwner getTo() {
        return to;
    }

    /**
     * Optional.
     * @returns the data set by the client making a payment that will be attached to the transfer and sent back when a notification related to this
     * payment is issued by Cyclos (likely to the same client).<br>
     * It depends on the client side then there is no guarantee of uniqueness between different clients.<br>
     * Note: <b>It has nothing to do with the traceNumber field in the parent class (used to tag transactions and query by these value).</b>
     */
    public String getTraceData() {
        return traceData;
    }

    public String getTraceNumber() {
        return traceNumber;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public boolean isShowScheduledToReceiver() {
        return showScheduledToReceiver;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public void setChannel(final String channel) {
        this.channel = channel;
    }

    public void setContext(final TransactionContext context) {
        this.context = context;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    @Override
    public void setCustomValues(final Collection<PaymentCustomFieldValue> customValues) {
        this.customValues = customValues;
    }

    public void setDate(final Calendar date) {
        this.date = date;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setFrom(final AccountOwner from) {
        this.from = from;
    }

    public void setPayments(final List<ScheduledPaymentDTO> payments) {
        this.payments = payments;
    }

    public void setReceiver(final Element receiver) {
        this.receiver = receiver;
    }

    public void setShowScheduledToReceiver(final boolean showScheduledToReceiver) {
        this.showScheduledToReceiver = showScheduledToReceiver;
    }

    public void setTicket(final Ticket ticket) {
        this.ticket = ticket;
    }

    public void setTo(final AccountOwner to) {
        this.to = to;
    }

    public void setTraceData(final String traceData) {
        this.traceData = traceData;
    }

    public void setTraceNumber(final String traceNumber) {
        this.traceNumber = traceNumber;
    }

    public void setTransferType(final TransferType transferType) {
        this.transferType = transferType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getCustomerRefId() {
        return customerRefId;
    }

    public void setCustomerRefId(String customerRefId) {
        this.customerRefId = customerRefId;
    }

    public Element getBy() {
        return by;
    }

    public void setBy(Element by) {
        this.by = by;
    }

    public String getParentTraceData() {
        return parentTraceData;
    }

    public void setParentTraceData(String parentTraceData) {
        this.parentTraceData = parentTraceData;
    }

    public Transfer getParent() {
        return parent;
    }

    public void setParent(Transfer parent) {
        this.parent = parent;
    }

	public String getMfsTransactionType() {
		return mfsTransactionType;
	}

	public void setMfsTransactionType(String mfsTransactionType) {
		this.mfsTransactionType = mfsTransactionType;
	}

	public String getExternalCustomer() {
		return externalCustomer;
	}

	public void setExternalCustomer(String externalCustomer) {
		this.externalCustomer = externalCustomer;
	}

	public String getSystemWiseTxnId() {
		return systemWiseTxnId;
	}

	public void setSystemWiseTxnId(String systemWiseTxnId) {
		this.systemWiseTxnId = systemWiseTxnId;
	}

	public Calendar getSystemWiseReqTime() {
		return systemWiseReqTime;
	}

	public void setSystemWiseReqTime(Calendar systemWiseReqTime) {
		this.systemWiseReqTime = systemWiseReqTime;
	}
}
