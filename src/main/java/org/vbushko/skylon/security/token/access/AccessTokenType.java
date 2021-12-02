package org.vbushko.skylon.security.token.access;

import lombok.Getter;

public enum AccessTokenType {

    BEARER("Bearer");

    @Getter
    private final String type;

    AccessTokenType(String type) {
        this.type = type;
    }
}
