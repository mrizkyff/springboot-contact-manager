package com.mrizkyff.contactmanager.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String exception) {
        super(exception);
    }
}
