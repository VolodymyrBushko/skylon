package org.vbushko.skylon.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vbushko.skylon.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
