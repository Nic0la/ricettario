package com.exercise.uno.service;

import com.exercise.uno.mapper.CategoryMapper;
import com.exercise.uno.mapper.CycleAvoidingMappingContext;
import com.exercise.uno.mapper.RecipeMapper;
import com.exercise.uno.models.dto.CategoryDTO;
import com.exercise.uno.models.dto.RecipeDTO;
import com.exercise.uno.models.entity.Category;
import com.exercise.uno.models.entity.Recipe;
import com.exercise.uno.repository.CategoryRepository;
import com.exercise.uno.repository.RecipeRepository;
import com.exercise.uno.service.exception.EntityNotFoundException;
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


    public RecipeDTO getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No recipe with this id " +id +" was found"));
        return RecipeMapper.INSTANCE.toDTO(recipe);
    }

    public RecipeDTO findRecipeById(Long id) throws IllegalArgumentException, EntityNotFoundException
    {
        if(id<=0)
            throw new IllegalArgumentException(id + " is not a valid id");
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(recipe.isEmpty())
            throw new EntityNotFoundException("No recipe with this id " +id +" was found");
        return RecipeMapper.INSTANCE.toDTO(recipe.get());
    }

    public RecipeDTO findRecipeByName(String name) throws EntityNotFoundException {

        Recipe recipe = recipeRepository.findByName(name);
        if(recipe==null)
            throw new EntityNotFoundException("No recipe with this name " +name +" was found");
        return RecipeMapper.INSTANCE.toDTO(recipe);
    }

    public List<RecipeDTO> findAllRecipe() {
        List<Recipe> recipes = recipeRepository.findAll();
        return RecipeMapper.INSTANCE.toDTOList(recipes);
    }

}
