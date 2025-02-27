package com.exercise.uno.controller;

import com.exercise.uno.models.dto.RecipeDTO;
import com.exercise.uno.models.dto.UserDTO;
import com.exercise.uno.models.entity.LoginRequest;
import com.exercise.uno.models.entity.RecipeRequest;
import com.exercise.uno.service.AuthService;
import com.exercise.uno.service.JwtUtil;
import com.exercise.uno.service.UserService;
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
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    @RequestMapping("/add_recipe")
    @Transactional
    public ResponseEntity<?> addRecipe2(@RequestBody RecipeRequest request) throws EntityNotFoundException {
        try{
            userService.addRecipe(request.getUsername(), request.getRecipe());
            return ResponseEntity.ok("Recipe added successfully");
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body("Recipe not added");
        }
    }

    @GetMapping
    @RequestMapping("/all")
    @PostAuthorize( "hasRole('ADMIN')")
    public List<UserDTO> getAllUser(){
        return userService.findAllUsers();
    }

}
