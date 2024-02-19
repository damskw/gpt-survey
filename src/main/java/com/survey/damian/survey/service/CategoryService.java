package com.survey.damian.survey.service;

import com.survey.damian.survey.controller.dto.CategoryDto;
import com.survey.damian.survey.mapper.CategoryMapper;
import com.survey.damian.survey.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryDto addNewCategory(CategoryDto dto) {
        var category = categoryMapper.mapToEntity(dto);
        var savedCategory = categoryRepository.save(category);
        return categoryMapper.mapToDto(savedCategory);
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::mapToDto)
                .toList();
    }
}
