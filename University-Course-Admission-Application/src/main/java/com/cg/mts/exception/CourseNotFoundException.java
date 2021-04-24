package com.cg.mts.exception;

public class CourseNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public CourseNotFoundException(String errormessage) {
		super(errormessage);
	}

}
