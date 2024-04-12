package com.contact.finder.exception;

public class CustomExceptionBadRequest extends RuntimeException{

	public CustomExceptionBadRequest(String message) {
        super(message);
    }
}
