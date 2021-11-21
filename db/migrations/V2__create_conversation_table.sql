CREATE TABLE IF NOT EXISTS conversation
(
    id            BIGINT                        NOT NULL,
    title         VARCHAR(50)                   NOT NULL,
    description   VARCHAR(255),
    image         VARCHAR(255),
    owner_id      BIGINT                        NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE   NOT NULL,
    updated_at    TIMESTAMP WITHOUT TIME ZONE   NOT NULL,

    CONSTRAINT PK_CONVERSATION PRIMARY KEY (id),

    CONSTRAINT FK_CONVERSATION_USR FOREIGN KEY (owner_id) REFERENCES usr (id)
);
