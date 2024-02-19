package com.survey.damian.survey.mapper;

import com.survey.damian.survey.controller.dto.SurveyDto;
import com.survey.damian.survey.entity.Survey;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SurveyMapper {

    private final SubcategoryMapper subcategoryMapper;
    private final QuestionMapper questionMapper;

    public Survey mapToEntity(SurveyDto dto) {
        return new Survey(
                dto.name(),
                dto.subcategories().stream()
                        .map(subcategoryMapper::mapToEntity)
                        .toList());
    }

    public SurveyDto mapToDto(Survey survey) {
        return new SurveyDto(
                survey.getId(),
                survey.getName(),
                survey.getSubcategories().stream()
                        .map(subcategoryMapper::mapToDto)
                        .toList(),
                survey.getQuestions().stream()
                        .map(questionMapper::mapToDto)
                        .toList());
    }

}
