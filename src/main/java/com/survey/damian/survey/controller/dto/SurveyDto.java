package com.survey.damian.survey.controller.dto;

import java.util.List;

public record SurveyDto(Long id, String name, List<SubcategoryDto> subcategories, List<QuestionDto> questions) {
}
