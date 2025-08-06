package com.haddad.blogin.repository;

import com.haddad.blogin.model.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerRepo extends JpaRepository<Blogger, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
