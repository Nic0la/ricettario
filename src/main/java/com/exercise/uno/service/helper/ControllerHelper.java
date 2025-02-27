package com.exercise.uno.service.helper;

import com.exercise.uno.models.dto.RecipeDTO;
import com.exercise.uno.models.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ControllerHelper {

    RecipeDTO getRecipeById(Long id);

    RecipeDTO findRecipeById(Long id);
    RecipeDTO findRecipeByName(String name);

    List<RecipeDTO> findAllRecipe();

    ResponseEntity<String> deleteRecipeById(Long id);

    List<UserDTO> findAllUsers();

    void addRecipe(RecipeDTO recipeDTO);

}
