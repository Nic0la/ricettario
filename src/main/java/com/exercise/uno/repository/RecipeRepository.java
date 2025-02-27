package com.exercise.uno.repository;

import com.exercise.uno.models.entity.Category;
import com.exercise.uno.models.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findByName(String name);

    Recipe findRecipesByCategory (Optional<Category> category);

    List<Recipe> findAllRecipesByCategory(Optional<Category> category);

}
