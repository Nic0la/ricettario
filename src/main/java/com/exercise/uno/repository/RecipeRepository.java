package com.exercise.uno.repository;

import com.exercise.uno.modules.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findByName(String name);
}
