package org.vbushko.skylon.security;

import lombok.Getter;

public enum TokenType {

    BEARER("Bearer");

    @Getter
    private final String type;

    TokenType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
