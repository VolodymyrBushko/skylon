package org.vbushko.skylon.usr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vbushko.skylon.usr.entity.Usr;

@Repository
public interface UsrRepository extends JpaRepository<Usr, Long> {
}
