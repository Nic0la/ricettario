package com.exercise.uno.controller;

import com.exercise.uno.controller.helper.ControllerHelper;
import com.exercise.uno.modules.dto.RecipeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
