package com.tejait.batch15.exceptions;

public class MobileNumberAlreadyExists  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MobileNumberAlreadyExists() {
		super();
	}
	
	public MobileNumberAlreadyExists(String msg) {
		super(msg);
	}
	
	

}
