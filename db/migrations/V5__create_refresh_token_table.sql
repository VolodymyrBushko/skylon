CREATE TABLE IF NOT EXISTS refresh_token
(
    id           BIGINT                        NOT NULL,
    token        VARCHAR(36)                   NOT NULL,
    user_id      BIGINT                        NOT NULL,
    expired_at   TIMESTAMP WITHOUT TIME ZONE   NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE   NOT NULL,
    updated_at   TIMESTAMP WITHOUT TIME ZONE   NOT NULL,

    CONSTRAINT PK_REFRESH_TOKEN PRIMARY KEY (id),

    CONSTRAINT FK_REFRESH_TOKEN_USER FOREIGN KEY (user_id) REFERENCES "user" (id),

    CONSTRAINT UQ_REFRESH_TOKEN_TOKEN UNIQUE (token),
    CONSTRAINT UQ_REFRESH_TOKEN_USER_ID UNIQUE (user_id)
);

CREATE SEQUENCE IF NOT EXISTS refresh_token_id_sequence
    INCREMENT 1
    MINVALUE 1
    START 1
    OWNED BY refresh_token.id;
