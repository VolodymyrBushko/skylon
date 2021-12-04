package org.vbushko.skylon.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vbushko.skylon.user.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    boolean existsByLoginOrEmail(String login, String email);

    @Query("SELECT u FROM RefreshToken t LEFT JOIN t.user u WHERE t.token = ?1")
    Optional<User> findByRefreshToken(String token);
}
