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

CREATE SEQUENCE IF NOT EXISTS conversation_id_sequence
    INCREMENT 1
    MINVALUE 1
    START 1
    OWNED BY conversation.id;
