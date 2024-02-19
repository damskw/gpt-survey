package com.survey.damian.survey.service;

import com.survey.damian.survey.controller.dto.QuestionDto;
import com.survey.damian.survey.entity.Question;
import com.survey.damian.survey.mapper.QuestionMapper;
import com.survey.damian.survey.repository.QuestionRepository;
import org.assertj.core.api.Assertions;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuestionMapper questionMapper;

    @InjectMocks
    private QuestionService questionService;


    @Test
    void shouldReturnAddedQuestion() {
        // Given
        var questionDto = Instancio.of(QuestionDto.class).create();
        var question = Instancio.of(Question.class).create();
        Mockito.when(questionMapper.mapToEntity(questionDto)).thenReturn(question);
        Mockito.when(questionRepository.save(Mockito.any(Question.class))).thenAnswer(i -> i.getArgument(0));
        Mockito.when(questionMapper.mapToDto(question)).thenReturn(questionDto);

        // When
        var actual = questionService.addNewQuestion(questionDto);

        // Then
        Assertions.assertThat(actual).isEqualTo(questionDto);
    }

    @Test
    void shouldReturnListOfQuestionDto() {
        // Given
        var surveyId = Instancio.of(Long.class).create();
        var questions = Instancio.ofList(Question.class).create();
        Mockito.when(questionRepository.findAllBySurveyId(surveyId)).thenReturn(questions);
        Mockito.when(questionMapper.mapToDto(Mockito.any(Question.class))).thenReturn(Instancio.of(QuestionDto.class).create());

        // When
        var actual = questionService.getQuestionsForSurvey(surveyId);

        // Then
        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual.get(0)).isInstanceOf(QuestionDto.class);
        Assertions.assertThat(questions.size()).isEqualTo(actual.size());
    }


}