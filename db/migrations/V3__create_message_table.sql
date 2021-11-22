CREATE TABLE IF NOT EXISTS message
(
    id                BIGINT                        NOT NULL,
    content           VARCHAR(255)                  NOT NULL,
    sender_id         BIGINT                        NOT NULL,
    conversation_id   BIGINT                        NOT NULL,
    created_at        TIMESTAMP WITHOUT TIME ZONE   NOT NULL,
    updated_at        TIMESTAMP WITHOUT TIME ZONE   NOT NULL,

    CONSTRAINT PK_MESSAGE PRIMARY KEY (id),

    CONSTRAINT FK_MESSAGE_USER FOREIGN KEY (sender_id) REFERENCES usr (id),
    CONSTRAINT FK_MESSAGE_CONVERSATION FOREIGN KEY (conversation_id) REFERENCES conversation (id)
);
