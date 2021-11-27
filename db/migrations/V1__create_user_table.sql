CREATE TABLE IF NOT EXISTS "user"
(
    id            BIGINT                        NOT NULL,
    first_name    VARCHAR(25)                   NOT NULL,
    last_name     VARCHAR(25)                   NOT NULL,
    login         VARCHAR(50)                   NOT NULL,
    email         VARCHAR(100)                  NOT NULL,
    description   VARCHAR(255),
    image         VARCHAR(255),
    password      VARCHAR(510)                  NOT NULL,
    age           INTEGER                       NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE   NOT NULL,
    updated_at    TIMESTAMP WITHOUT TIME ZONE   NOT NULL,

    CONSTRAINT PK_USER PRIMARY KEY (id),

    CONSTRAINT UQ_USER_LOGIN UNIQUE (login),
    CONSTRAINT UQ_USER_EMAIL UNIQUE (email),

    CHECK (age >= 0)
);

CREATE SEQUENCE IF NOT EXISTS user_id_sequence
    INCREMENT 1
    MINVALUE 1
    START 1
    OWNED BY "user".id;
