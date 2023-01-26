package org.miu.mpp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

final public class CheckoutRecord implements Serializable{

	private static final long serialVersionUID = 7274497633871237909L;
	private List<CheckoutEntry> entries;
	private String memberId;
	
	public List<CheckoutEntry> getEntries() {
		return entries;
	}
	
	CheckoutRecord(String memberId){
		this.memberId = memberId;
		this.entries = new ArrayList<>();
	}
	
	public void addCheckoutEntry(CheckoutEntry entry) {
		this.entries.add(entry);
	}

	public String getMemberId() {
		return memberId;
	}

}
