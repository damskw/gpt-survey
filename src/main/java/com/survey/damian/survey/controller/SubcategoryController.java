package com.survey.damian.survey.controller;

import com.survey.damian.survey.controller.dto.SubcategoryDto;
import com.survey.damian.survey.service.SubcategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subcategory")
@AllArgsConstructor
public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public SubcategoryDto addNewSubcategory(@RequestBody SubcategoryDto dto) {
        return subcategoryService.addNewSubcategory(dto);
    }

    @GetMapping(params = "categoryId")
    public List<SubcategoryDto> getSubcategoriesForCategory(@RequestParam Long categoryId) {
        return subcategoryService.getForCategory(categoryId);
    }

}
