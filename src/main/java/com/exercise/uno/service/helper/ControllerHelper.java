package com.exercise.uno.service.helper;

import com.exercise.uno.models.dto.RecipeDTO;

import java.util.List;

public interface ControllerHelper {

    RecipeDTO getRecipeById(Long id);

    RecipeDTO findRecipeById(Long id);
    RecipeDTO findRecipeByName(String name);

    List<RecipeDTO> findAllRecipe();

    void deleteRecipeById(Long id);

}
