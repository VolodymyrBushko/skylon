package org.vbushko.skylon.exception;

public class AccessTokenException extends RuntimeException {

    public AccessTokenException() {
        super();
    }

    public AccessTokenException(String message) {
        super(message);
    }
}
