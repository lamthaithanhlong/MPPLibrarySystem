package org.miu.mpp.ui.checkoutbook;

import java.io.Serializable;

public class CheckoutBookException extends Exception implements Serializable{

	public CheckoutBookException() {
		super();
	}
	public CheckoutBookException(String msg) {
		super(msg);
	}
	public CheckoutBookException(Throwable t) {
		super(t);
	}
	
	private static final long serialVersionUID = 163192254933355051L;

}
