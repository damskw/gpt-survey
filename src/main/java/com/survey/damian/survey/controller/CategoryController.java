package com.survey.damian.survey.controller;

import com.survey.damian.survey.controller.dto.CategoryDto;
import com.survey.damian.survey.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto addNewCategory(@RequestBody CategoryDto dto) {
        return categoryService.addNewCategory(dto);
    }

}
