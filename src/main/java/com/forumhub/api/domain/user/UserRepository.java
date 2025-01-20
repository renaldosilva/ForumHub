package com.forumhub.api.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);

    Page<User> findAllByActiveTrue(Pageable pageable);

    @Query("""
            SELECT user.active from User user
            WHERE user.id = :id
            """)
    boolean findAtivoById(Long id);
}
