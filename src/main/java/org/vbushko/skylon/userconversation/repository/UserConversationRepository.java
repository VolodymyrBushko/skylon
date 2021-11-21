package org.vbushko.skylon.userconversation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vbushko.skylon.userconversation.entity.UserConversation;
import org.vbushko.skylon.userconversation.entity.UserConversationId;

@Repository
public interface UserConversationRepository extends JpaRepository<UserConversation, UserConversationId> {
}
