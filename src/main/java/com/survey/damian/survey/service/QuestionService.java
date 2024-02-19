package com.survey.damian.survey.service;

import com.survey.damian.survey.controller.dto.QuestionDto;
import com.survey.damian.survey.mapper.QuestionMapper;
import com.survey.damian.survey.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;


    public QuestionDto addNewQuestion(QuestionDto dto) {
        var question = questionMapper.mapToEntity(dto);
        var questionDb = questionRepository.save(question);
        return questionMapper.mapToDto(questionDb);
    }

    public List<QuestionDto> getQuestionsForSurvey(Long surveyId) {
        return questionRepository.findAllBySurveyId(surveyId).stream()
                .map(questionMapper::mapToDto)
                .toList();
    }
}
