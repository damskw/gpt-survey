package com.survey.damian.survey.mapper;

import com.survey.damian.survey.controller.dto.CategoryDto;
import com.survey.damian.survey.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category mapToEntity(CategoryDto dto) { return new Category(dto.name()); }

    public CategoryDto mapToDto(Category category) { return new CategoryDto(category.getId(), category.getName()); }

}
