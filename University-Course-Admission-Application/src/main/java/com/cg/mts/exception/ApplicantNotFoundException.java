package com.cg.mts.exception;



public class ApplicantNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public ApplicantNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
