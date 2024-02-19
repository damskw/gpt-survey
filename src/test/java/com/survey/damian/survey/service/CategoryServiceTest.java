package com.survey.damian.survey.service;

import com.survey.damian.survey.controller.dto.CategoryDto;
import com.survey.damian.survey.entity.Category;
import com.survey.damian.survey.mapper.CategoryMapper;
import com.survey.damian.survey.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldReturnEmptyList() {
        // Given
        Mockito.when(categoryRepository.findAll()).thenReturn(List.of());

        // When
        var result = categoryService.getAllCategories();

        // Then
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnCategoryDto() {
        // Given
        var categoryDto = Instancio.of(CategoryDto.class).create();
        var category = Instancio.of(Category.class).create();
        Mockito.when(categoryMapper.mapToEntity(categoryDto)).thenReturn(category);
        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Mockito.when(categoryMapper.mapToDto(category)).thenReturn(categoryDto);

        // When
        var actual = categoryService.addNewCategory(categoryDto);

        // Then
        Assertions.assertThat(actual).isEqualTo(categoryDto);
    }

}