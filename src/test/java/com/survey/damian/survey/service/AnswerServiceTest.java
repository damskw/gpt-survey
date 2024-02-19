package com.survey.damian.survey.service;

import com.survey.damian.survey.controller.dto.AnswerDto;
import com.survey.damian.survey.entity.Answer;
import com.survey.damian.survey.entity.Question;
import com.survey.damian.survey.mapper.AnswerMapper;
import com.survey.damian.survey.repository.AnswerRepository;
import com.survey.damian.survey.repository.QuestionRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AnswerServiceTest {

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AnswerMapper answerMapper;

    @InjectMocks
    private AnswerService answerService;

    @Test
    void shouldAddNewAnswer() {
        // Given
        var answerDto = Instancio.of(AnswerDto.class).create();
        var answer = Instancio.of(Answer.class).create();
        var question = Instancio.of(Question.class).create();
        Mockito.when(answerMapper.mapToEntity(answerDto)).thenReturn(answer);
        Mockito.when(questionRepository.findById(answerDto.questionId())).thenReturn(Optional.of(question));
        Mockito.when(answerRepository.save(Mockito.any(Answer.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Mockito.when(answerMapper.mapToDto(answer)).thenReturn(answerDto);

        // When
        var result = answerService.addNewAnswer(answerDto);

        // Then
        Assertions.assertEquals(answerDto, result);
    }

    @Test
    void shouldSubmitSurveyAnswers() {
        // Given
        var answerDto1 = Instancio.of(AnswerDto.class).create();
        var answerDto2 = Instancio.of(AnswerDto.class).create();

        var answer1 = Instancio.of(Answer.class).create();
        var answer2 = Instancio.of(Answer.class).create();

        var question1 = Instancio.of(Question.class).create();
        var question2 = Instancio.of(Question.class).create();

        var answers = List.of(answerDto1, answerDto2);

        Mockito.when(answerMapper.mapToEntity(answerDto1)).thenReturn(answer1);
        Mockito.when(questionRepository.findById(answerDto1.questionId())).thenReturn(Optional.of(question1));
        Mockito.when(answerRepository.save(answer1)).thenReturn(answer1);
        Mockito.when(answerMapper.mapToDto(answer1)).thenReturn(answerDto1);

        Mockito.when(answerMapper.mapToEntity(answerDto2)).thenReturn(answer2);
        Mockito.when(questionRepository.findById(answerDto2.questionId())).thenReturn(Optional.of(question2));
        Mockito.when(answerRepository.save(answer2)).thenReturn(answer2);
        Mockito.when(answerMapper.mapToDto(answer2)).thenReturn(answerDto2);

        var expected = List.of(answerDto1, answerDto2);

        // When
        var actual = answerService.submitSurveyAnswers(answers);

        // Then
        Assertions.assertEquals(expected, actual);
    }



}