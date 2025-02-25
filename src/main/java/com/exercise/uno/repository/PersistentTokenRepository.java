package com.exercise.uno.repository;

import com.exercise.uno.domain.PersistentToken;
import com.exercise.uno.modules.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PersistentTokenRepository extends JpaRepository<PersistentToken, String> {

    List<PersistentToken> findByUser(User user);
}
