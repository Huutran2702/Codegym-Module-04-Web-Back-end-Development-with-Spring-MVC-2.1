package com.codegym.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DivideByZeroException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    public DivideByZeroException(String message) {
        super(message);
    }

    public DivideByZeroException() {

    }
}
