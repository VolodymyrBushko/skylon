package org.vbushko.skylon.exception;

public class RefreshTokenException extends RuntimeException {

    public RefreshTokenException() {
        super();
    }

    public RefreshTokenException(String message) {
        super(message);
    }
}
