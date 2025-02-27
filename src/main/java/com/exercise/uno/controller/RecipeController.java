package com.exercise.uno.controller;

import com.exercise.uno.models.entity.Recipe;
import com.exercise.uno.models.entity.User;
import com.exercise.uno.service.RecipeService;
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

    @Autowired
    RecipeService recipeService;

    @PostMapping
    public ResponseEntity<?> addRecipe(@RequestBody RecipeDTO recipeDTO){

        if(recipeDTO.getName() == null || recipeDTO.getName().isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        controllerHelper.addRecipe(recipeDTO);
        return ResponseEntity.ok("Recipe added successfully");
    }

    @GetMapping("/all")
    public List<RecipeDTO> getRecipes(){
        return controllerHelper.findAllRecipe();
    }

    @GetMapping("/{id}")
    public RecipeDTO getRecipeById(@PathVariable("id") Long id){
        Optional<RecipeDTO> recipe = Optional.ofNullable(controllerHelper.findRecipeById(id));

        if(recipe.isEmpty()){
            throw new IllegalArgumentException("Recipe not found");

        }else {
            return controllerHelper.getRecipeById(id);
        }
    }

    /**
     * Take al recipe with the same category
     * accept only the name of the category as param
     * @param name
     * @return
     */
    @GetMapping("/category/{name}")
    public List<RecipeDTO> getRecipesByCategory(@PathVariable("name") String name){
        return recipeService.findAllRecipesByCategory(name);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
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
