package com.exercise.uno.models.dto;

import com.exercise.uno.models.entity.Recipe;
import com.exercise.uno.models.entity.User;
import com.exercise.uno.repository.CategoryRepository;
import com.exercise.uno.repository.RecipeRepository;
import com.exercise.uno.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DTOConverter {

    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public RecipeDTO toDto(Recipe recipe) {
        RecipeDTO res = new RecipeDTO();
        res.setId(recipe.getId());
        res.setName(recipe.getName());
        //res.setCategory(rCategoryRepository.findById(recipe.getCategory().getId()).get().getName());

        return res;
    }

    public User DtoFromUser(UserDTO user) {
        User res = new User();
        res.setUsername(user.getUsername());
        res.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRecipes()!=null){
            res.setRecipes(user.getRecipes());
        }
        return res;
    }



}
