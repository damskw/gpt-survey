package com.survey.damian.survey.service;

import com.survey.damian.survey.controller.dto.SubcategoryDto;
import com.survey.damian.survey.exception.CategoryNotFoundException;
import com.survey.damian.survey.mapper.SubcategoryMapper;
import com.survey.damian.survey.repository.CategoryRepository;
import com.survey.damian.survey.repository.SubcategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;
    private final SubcategoryMapper subcategoryMapper;

    public SubcategoryDto addNewSubcategory(SubcategoryDto dto) {
        var category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException(dto.categoryId()));
        var subcategory = subcategoryMapper.mapToEntity(dto);
        subcategory.setCategory(category);
        var subcategoryDb = subcategoryRepository.save(subcategory);
        return subcategoryMapper.mapToDto(subcategoryDb);
    }

    public List<SubcategoryDto> getForCategory(Long categoryId) {
        return subcategoryRepository.findAllByCategoryId(categoryId).stream()
                .map(subcategoryMapper::mapToDto)
                .toList();
    }
}
