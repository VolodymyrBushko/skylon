CREATE TABLE IF NOT EXISTS usr
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

    CONSTRAINT PK_USR PRIMARY KEY (id),

    CONSTRAINT UQ_USR_LOGIN UNIQUE (login),
    CONSTRAINT UQ_USR_EMAIL UNIQUE (email),

    CHECK (age >= 0)
);
