package com.survey.damian.survey.service;

import com.survey.damian.survey.controller.dto.AnswerDto;
import com.survey.damian.survey.exception.QuestionNotFoundException;
import com.survey.damian.survey.mapper.AnswerMapper;
import com.survey.damian.survey.repository.AnswerRepository;
import com.survey.damian.survey.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final AnswerMapper answerMapper;


    public AnswerDto addNewAnswer(AnswerDto dto) {
        var answer = answerMapper.mapToEntity(dto);
        var question = questionRepository.findById(dto.questionId())
                .orElseThrow(() -> new QuestionNotFoundException(dto.questionId()));
        answer.setQuestion(question);
        var answerDb = answerRepository.save(answer);
        return answerMapper.mapToDto(answerDb);
    }

    public List<AnswerDto> submitSurveyAnswers(List<AnswerDto> answers) {
        return answers.stream()
                .map(this::addNewAnswer)
                .toList();
    }
}
