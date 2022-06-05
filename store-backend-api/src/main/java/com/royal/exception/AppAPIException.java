package com.royal.exception;

import org.springframework.http.HttpStatus;

/**
 *
 *@author Isaachome
 */
public class AppAPIException  extends RuntimeException{
	
	private static final long serialVersionUID = -4389123193411429426L;
	
	private HttpStatus httpStatus;
    private String message;
    

    public AppAPIException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public AppAPIException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	@Override
	public String getMessage() {
		return message;
	}

    

}
