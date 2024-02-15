package com.cardenas.exception;

public class BadRequestException extends RuntimeException {

    private static final String DESCRIPTION = "Bad Request Exception, Error";

    public BadRequestException(String detail) {
        super(String.format("%s - %s", DESCRIPTION, detail));
    }
}
