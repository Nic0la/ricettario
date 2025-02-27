package com.exercise.uno.controller;

import com.exercise.uno.models.dto.RecipeDTO;
import com.exercise.uno.models.dto.UserDTO;
import com.exercise.uno.models.entity.LoginRequest;
import com.exercise.uno.models.entity.RecipeRequest;
import com.exercise.uno.service.JwtUtil;
import com.exercise.uno.service.exception.EntityNotFoundException;
import com.exercise.uno.models.dto.DTOConverter;
import com.exercise.uno.models.entity.Recipe;
import com.exercise.uno.models.entity.User;
import com.exercise.uno.repository.RecipeRepository;
import com.exercise.uno.repository.UserRepository;
import com.exercise.uno.service.helper.ControllerHelper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    ControllerHelper ch;
    @Autowired
    JwtUtil jwtUtil;

//    @PostMapping
//    @RequestMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest request) throws EntityNotFoundException {
//        User user = userRepository.findByUsername(request.getUsername());
//        if(!user.getPassword().equals(request.getPassword()))
//            throw new EntityNotFoundException("Wrong password");
//        System.out.println(user.toString());
//        return ResponseEntity.ok("Login con username: " + request.getUsername() + " " + request.getPassword());
//    }

    @PostMapping
    @RequestMapping("/addRecipe")
    @Transactional
    public void addRecipe(@RequestBody RecipeRequest request) throws EntityNotFoundException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            System.out.println("Failed");
            throw new RuntimeException("Utente non autenticato");
        }

        User user = userRepository.findByUsername(request.getUsername());
        if(user == null)
            throw new EntityNotFoundException("User not found");
        Recipe recipe = recipeRepository.findByName(request.getRecipe());
        if(recipe==null)
            throw new EntityNotFoundException("Recipe not found");

        recipe.setUser(user);
        user.getRecipes().add(recipe);

        userRepository.save(user);
        recipeRepository.save(recipe);
    }

    @GetMapping
    @RequestMapping("/all")
    public List<UserDTO> getAllUser(){return ch.findAllUsers();}

}
