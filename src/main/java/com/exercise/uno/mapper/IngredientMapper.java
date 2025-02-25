package com.exercise.uno.mapper;

import com.exercise.uno.modules.dto.IngredientDTO;
import com.exercise.uno.modules.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    IngredientDTO toDTO(Ingredient ingredient);

    Ingredient toEntity(IngredientDTO ingredientDTO);
}
