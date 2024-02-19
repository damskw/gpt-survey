package com.survey.damian.survey.mapper;

import com.survey.damian.survey.controller.dto.AnswerDto;
import com.survey.damian.survey.controller.dto.OpenAiResponse;
import com.survey.damian.survey.controller.dto.QuestionDto;
import com.survey.damian.survey.entity.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class QuestionMapper {

    private final AnswerMapper answerMapper;

    public Question mapToEntity(QuestionDto dto) {
        return new Question(dto.text());
    }

    public QuestionDto mapToDto(Question question) {
        List<AnswerDto> answerDtos = Optional.ofNullable(question.getAnswers())
                .orElse(Collections.emptyList())
                .stream()
                .map(answerMapper::mapToDto)
                .toList();
        return new QuestionDto(
                question.getId(),
                question.getText(),
                answerDtos);
    }

    public List<Question> mapFromOpenAiResponse(OpenAiResponse response) {
        return response.choices().stream()
                .map(c -> new Question(c.message().content()))
                .toList();
    }
}
