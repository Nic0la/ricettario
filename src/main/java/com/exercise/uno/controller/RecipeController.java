package com.exercise.uno.controller;

import com.exercise.uno.models.entity.Recipe;
import com.exercise.uno.models.entity.User;
import com.exercise.uno.service.helper.ControllerHelper;
import com.exercise.uno.models.dto.RecipeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/recipe")
public class RecipeController
{

    @Autowired
    ControllerHelper controllerHelper;

    @GetMapping
    public List<RecipeDTO> getRecipes(){return controllerHelper.findAllRecipe();}

    @GetMapping("/{id}")
    public RecipeDTO getRecipeById(@PathVariable("id") Long id){return controllerHelper.getRecipeById(id);}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRecipeById(@PathVariable("id") Long id){
        Optional<RecipeDTO> recipe = Optional.ofNullable(controllerHelper.findRecipeById(id));
        if(recipe.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        RecipeDTO existingRecipe = recipe.get();

        controllerHelper.deleteRecipeById(id);
        return ResponseEntity.ok("Recipe is deleted");

    }

}
