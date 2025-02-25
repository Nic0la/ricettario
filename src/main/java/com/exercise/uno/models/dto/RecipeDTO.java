package com.exercise.uno.models.dto;

import java.util.HashSet;
import java.util.Set;

public class RecipeDTO {

    private Long id;

    private String name;

    private CategoryDTO category;

    private Set<IngredientDTO> ingredients = new HashSet<>();

    public Set<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
}
