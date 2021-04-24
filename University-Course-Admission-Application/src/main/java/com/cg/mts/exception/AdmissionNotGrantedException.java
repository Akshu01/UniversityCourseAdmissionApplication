package com.cg.mts.exception;

public class AdmissionNotGrantedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public AdmissionNotGrantedException(String errorMessage) {
		super(errorMessage);
	}

}
