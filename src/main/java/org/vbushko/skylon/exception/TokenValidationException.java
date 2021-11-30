package org.vbushko.skylon.exception;

public class TokenValidationException extends RuntimeException {

    public TokenValidationException() {
        super();
    }

    public TokenValidationException(String message) {
        super(message);
    }
}
