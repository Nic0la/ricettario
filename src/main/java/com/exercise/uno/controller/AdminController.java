package com.exercise.uno.controller;

import com.exercise.uno.controller.helper.ControllerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    ControllerHelper controllerHelper;

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable("id") Long id){
        controllerHelper.deleteRecipeById(id);
    }
}




