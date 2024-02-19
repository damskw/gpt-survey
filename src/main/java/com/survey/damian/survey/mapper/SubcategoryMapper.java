package com.survey.damian.survey.mapper;

import com.survey.damian.survey.controller.dto.SubcategoryDto;
import com.survey.damian.survey.entity.Subcategory;
import org.springframework.stereotype.Component;

@Component
public class SubcategoryMapper {

    public Subcategory mapToEntity(SubcategoryDto dto) { return new Subcategory(dto.name()); }

    public SubcategoryDto mapToDto(Subcategory subcategory) {
        return new SubcategoryDto(subcategory.getId(), subcategory.getCategory().getId(), subcategory.getName());
    }

}
