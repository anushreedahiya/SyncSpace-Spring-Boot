package com.syncspace.backend.repository;

import com.syncspace.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Custom method to find a user by email
    Optional<User> findByEmail(String email);
}
