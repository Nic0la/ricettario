package com.exercise.uno.mapper;

import com.exercise.uno.models.dto.RecipeDTO;
import com.exercise.uno.models.entity.Recipe;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface RecipeMapper {

    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    RecipeDTO toDTO(Recipe recipe);

    Recipe toEntity(RecipeDTO recipeDTO);

    List<RecipeDTO> toDTOList(List<Recipe> recipes);
}
