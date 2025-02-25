package com.exercise.uno.controller;

import com.exercise.uno.controller.exception.EntityNotFoundException;
import com.exercise.uno.modules.dto.DTOConverter;
import com.exercise.uno.modules.entity.Recipe;
import com.exercise.uno.modules.entity.User;
import com.exercise.uno.repository.RecipeRepository;
import com.exercise.uno.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    DTOConverter dtoConverter;

    public void addRecipe(@RequestBody String recipeName, @RequestBody String username) throws EntityNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new EntityNotFoundException("User not found");
        Recipe recipe = recipeRepository.findByName(recipeName);
        if(recipe==null)
            throw new EntityNotFoundException("Recipe not found");
        user.getRecipes().add(recipe);
        userRepository.save(user);
    }

}
