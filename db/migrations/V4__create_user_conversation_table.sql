CREATE TABLE IF NOT EXISTS user_conversation
(
    user_id           BIGINT                        NOT NULL,
    conversation_id   BIGINT                        NOT NULL,
    created_at        TIMESTAMP WITHOUT TIME ZONE   NOT NULL,
    updated_at        TIMESTAMP WITHOUT TIME ZONE   NOT NULL,

    CONSTRAINT PK_USER_CONVERSATION PRIMARY KEY (user_id, conversation_id),

    CONSTRAINT FK_USER FOREIGN KEY (user_id) REFERENCES "user" (id),
    CONSTRAINT FK_CONVERSATION FOREIGN KEY (conversation_id) REFERENCES "user" (id),

    CONSTRAINT UQ_USER_CONVERSATION_USER_ID_CONVERSATION_ID UNIQUE (user_id, conversation_id)
);
