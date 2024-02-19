package com.survey.damian.survey.controller.dto;

import java.util.List;

public record QuestionDto(Long id, String text, List<AnswerDto> answers) {
}
