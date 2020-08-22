package com.lti.exception;

public class RetailerServiceException extends RuntimeException{

	public RetailerServiceException(String message) {
		super(message);
	}

	public RetailerServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RetailerServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public RetailerServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RetailerServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
