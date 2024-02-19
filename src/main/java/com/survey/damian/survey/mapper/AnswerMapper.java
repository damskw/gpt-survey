package com.survey.damian.survey.mapper;

import com.survey.damian.survey.controller.dto.AnswerDto;
import com.survey.damian.survey.entity.Answer;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {

    public Answer mapToEntity(AnswerDto dto) {
        return new Answer(
                dto.answer()
        );
    }

    public AnswerDto mapToDto(Answer answer) {
        return new AnswerDto(
                answer.getId(),
                answer.getText(),
                answer.getQuestion().getId()
        );
    }


}
