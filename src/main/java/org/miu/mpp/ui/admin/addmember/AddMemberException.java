package org.miu.mpp.ui.admin.addmember;

import java.io.Serializable;

public class AddMemberException extends Exception implements Serializable {

	public AddMemberException() {
		super();
	}
	public AddMemberException(String msg) {
		super(msg);
	}
	public AddMemberException(Throwable t) {
		super(t);
	}
	
	private static final long serialVersionUID = 163192254933355051L;

}
