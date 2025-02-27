package com.exercise.uno.service.helper;

import com.exercise.uno.mapper.UserMapper;
import com.exercise.uno.models.dto.UserDTO;
import com.exercise.uno.models.entity.User;
import com.exercise.uno.repository.UserRepository;
import com.exercise.uno.service.exception.EntityNotFoundException;
import com.exercise.uno.models.dto.RecipeDTO;
import com.exercise.uno.models.entity.Recipe;
import com.exercise.uno.repository.RecipeRepository;
import com.exercise.uno.mapper.CycleAvoidingMappingContext;
import com.exercise.uno.mapper.RecipeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ControllerHelperImp implements ControllerHelper {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UserRepository userRepo;

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
        List<Recipe> recipes = recipeRepository.findAll();
        return RecipeMapper.INSTANCE.toDTOList(recipes);
    }

    @Override
    public ResponseEntity<String> deleteRecipeById(Long id) {
        try {
            recipeRepository.deleteById(id);
            return ResponseEntity.ok("Delete riuscito");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ricetta non trovata con ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'eliminazione della ricetta");
        }
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepo.findAll();
        return UserMapper.INSTANCE.toDTOList(users);
    }

    @Override
    public void addRecipe(RecipeDTO recipeDTO) {
        recipeRepository.save(RecipeMapper.INSTANCE.toEntity(recipeDTO));
    }




}
