package com.exercise.uno.repository;

import com.exercise.uno.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String Username);
    Boolean existsByUsername(String Username);
}
