package com.tracked.api.repository;

import com.tracked.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findById(String id);

    boolean existsByEmail(String email);

}
