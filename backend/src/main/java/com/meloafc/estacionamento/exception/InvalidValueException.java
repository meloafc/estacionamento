package com.meloafc.estacionamento.exception;

public class InvalidValueException extends BaseException {
    private static final long serialVersionUID = 1L;

    public InvalidValueException(String message, String... args) {
        super(message, args);
    }

    public InvalidValueException(String message, Throwable cause, String... args) {
        super(message, cause, args);
    }
}
