package com.meloafc.estacionamento.exception;

public final class NotFoundException extends BaseException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String message, String... args) {
        super(message, args);
    }

    public NotFoundException(String message, Throwable cause, String... args) {
        super(message, cause, args);
    }

}
