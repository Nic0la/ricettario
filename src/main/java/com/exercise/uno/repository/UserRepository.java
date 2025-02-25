package com.exercise.uno.repository;

import com.exercise.uno.modules.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String Username);
    Boolean existsByUsername(String Username);
}
