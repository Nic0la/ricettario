package com.exercise.uno.controller;

import com.exercise.uno.service.exception.EntityNotFoundException;
import com.exercise.uno.models.dto.DTOConverter;
import com.exercise.uno.models.entity.Recipe;
import com.exercise.uno.models.entity.User;
import com.exercise.uno.repository.RecipeRepository;
import com.exercise.uno.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping
    @RequestMapping("/login")
    public User login(@RequestBody String username, @RequestBody String password) throws EntityNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new EntityNotFoundException("User not found");
        if(! user.getPassword().equals(encoder.encode(password)))
            throw new EntityNotFoundException("Wrong password");
        return user;
    }

    @PostMapping
    @RequestMapping("/addRecipe")
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
