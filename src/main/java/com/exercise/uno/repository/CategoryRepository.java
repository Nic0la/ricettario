package com.exercise.uno.repository;

import com.exercise.uno.modules.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
