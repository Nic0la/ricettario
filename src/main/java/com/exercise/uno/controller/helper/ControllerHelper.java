package com.exercise.uno.controller.helper;

import com.exercise.uno.modules.dto.RecipeDTO;

import java.util.List;

public interface ControllerHelper {

    RecipeDTO getRecipeById(Long id);

    RecipeDTO findRecipeById(Long id);
    RecipeDTO findRecipeByName(String name);

    List<RecipeDTO> findAllRecipe();

    void deleteRecipeById(Long id);

}
