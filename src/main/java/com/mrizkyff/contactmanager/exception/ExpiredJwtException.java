package com.mrizkyff.contactmanager.exception;

public class ExpiredJwtException extends RuntimeException {
    public ExpiredJwtException(String exception) {
        super(exception);
    }
}
