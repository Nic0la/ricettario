package com.exercise.uno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exercise.uno.models.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
