package org.vbushko.skylon.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vbushko.skylon.message.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
