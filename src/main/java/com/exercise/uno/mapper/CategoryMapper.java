package com.exercise.uno.mapper;

import com.exercise.uno.models.dto.CategoryDTO;
import com.exercise.uno.models.entity.Category;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Named("RCategoryMapper")
@Mapper(uses = {CategoryMapper.class, RecipeMapper.class})
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO categoryDTO);

}
