package nl.strohalm.cyclos.mfs.models.transactions;

import nl.strohalm.cyclos.services.transactions.BulkPaymentResult;

public class TxnPair {
	TxnRequest request;
	BulkPaymentResult paymentResult;

	public TxnRequest getRequest() {
		return request;
	}

	public void setRequest(TxnRequest request) {
		this.request = request;
	}

	public BulkPaymentResult getPaymentResult() {
		return paymentResult;
	}

	public void setPaymentResult(BulkPaymentResult paymentResult) {
		this.paymentResult = paymentResult;
	}

}
