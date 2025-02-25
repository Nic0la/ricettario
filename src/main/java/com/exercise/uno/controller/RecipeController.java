package com.exercise.uno.controller;

import com.exercise.uno.service.helper.ControllerHelper;
import com.exercise.uno.models.dto.RecipeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @DeleteMapping("/{id}")
    @PostAuthorize("hasRole('ADMIN')")
    public void deleteRecipeById(@PathVariable("id") Long id){controllerHelper.deleteRecipeById(id);}

}
