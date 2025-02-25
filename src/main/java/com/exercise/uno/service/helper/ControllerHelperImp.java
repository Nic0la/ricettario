package com.exercise.uno.service.helper;

import com.exercise.uno.service.exception.EntityNotFoundException;
import com.exercise.uno.models.dto.DTOConverter;
import com.exercise.uno.models.dto.RecipeDTO;
import com.exercise.uno.models.entity.Recipe;
import com.exercise.uno.repository.RecipeRepository;
import com.exercise.uno.mapper.CycleAvoidingMappingContext;
import com.exercise.uno.mapper.RecipeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ControllerHelperImp implements ControllerHelper {

    @Autowired
    RecipeMapper recipeMapper;

    @Autowired
    RecipeRepository recipeRepository;

    //Nuovo Metodo:
    public RecipeDTO getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No recipe with this id " +id +" was found"));
        return RecipeMapper.INSTANCE.toDTO(recipe);
    }


    @Override
    public RecipeDTO findRecipeById(Long id) throws IllegalArgumentException, EntityNotFoundException
    {
        if(id<=0)
            throw new IllegalArgumentException(id + " is not a valid id");

        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(recipe.isEmpty())
            throw new EntityNotFoundException("No recipe with this id " +id +" was found");

        return RecipeMapper.INSTANCE.toDTO(recipe.get());
    }

    @Override
    public RecipeDTO findRecipeByName(String name) throws EntityNotFoundException {

        Recipe recipe = recipeRepository.findByName(name);
        if(recipe==null)
            throw new EntityNotFoundException("No recipe with this name " +name +" was found");
        return RecipeMapper.INSTANCE.toDTO(recipe);
    }

    @Override
    public List<RecipeDTO> findAllRecipe() {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        List<Recipe> recipes = recipeRepository.findAll();
        return RecipeMapper.INSTANCE.toDTOList(recipes);
    }

    @Override
    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }
}
