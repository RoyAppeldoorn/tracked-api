package com.tracked.api.service;

import com.tracked.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthService extends JpaRepository<User, String> {
}
