package com.tejait.batch15.exceptions;

public class MailAlreadyExists extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MailAlreadyExists() {
		super();
	}

	public MailAlreadyExists(String msg) {
		super(msg);
	}
}
