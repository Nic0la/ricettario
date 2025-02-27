package com.exercise.uno.service;

import com.exercise.uno.mapper.CategoryMapper;
import com.exercise.uno.mapper.RecipeMapper;
import com.exercise.uno.models.dto.CategoryDTO;
import com.exercise.uno.models.dto.RecipeDTO;
import com.exercise.uno.models.entity.Category;
import com.exercise.uno.models.entity.Recipe;
import com.exercise.uno.repository.CategoryRepository;
import com.exercise.uno.repository.RecipeRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<RecipeDTO> findAllRecipesByCategory(String name){

        Optional<Category> cat = categoryRepository.findByName(name);
        if(cat.isEmpty())
            throw new IllegalArgumentException();
        List<Recipe>res = recipeRepository.findAllRecipesByCategory(cat);
        if(res.isEmpty())
            throw new ObjectNotFoundException(res, "Recipe with category: " + name + "wasn't found");
        return RecipeMapper.INSTANCE.toDTOList(res);
    }




}
