package com.exercise.uno.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ingredient extends BaseEntity {

    private String ingredientName;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

}
