package com.api.cancha.exception;

public class CanchaIsNotExistException extends RuntimeException {
    public CanchaIsNotExistException(String message) {
        super(message);
    }
}
