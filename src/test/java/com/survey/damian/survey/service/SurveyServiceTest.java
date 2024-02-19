package com.survey.damian.survey.service;

import com.survey.damian.survey.controller.dto.SurveyDto;
import com.survey.damian.survey.entity.Survey;
import com.survey.damian.survey.exception.SurveyNotFoundException;
import com.survey.damian.survey.mapper.SurveyMapper;
import com.survey.damian.survey.repository.SurveyRepository;
import org.assertj.core.api.Assertions;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class SurveyServiceTest {
    @Mock
    private SurveyMapper surveyMapper;
    @Mock
    private SurveyRepository surveyRepository;

    @InjectMocks
    private SurveyService surveyService;


    @Test
    void shouldGetSurveyDto() {
        // Given
        var surveyId = 1L;
        var survey = Instancio.of(Survey.class).create();
        var surveyDto = Instancio.of(SurveyDto.class).create();
        Mockito.when(surveyRepository.findById(surveyId)).thenReturn(Optional.of(survey));
        Mockito.when(surveyMapper.mapToDto(survey)).thenReturn(surveyDto);

        // When
        var result = surveyService.getSingleSurvey(surveyId);

        // Then
        assertNotNull(result);
        assertEquals(surveyDto.id(), result.id());
        assertEquals(surveyDto.name(), result.name());
    }

    @Test
    void shouldReturnEmptyListWhenNoSurveys() {
        // When
        var actual = surveyService.getAllSurveys();
        // Then
        Assertions.assertThat(actual).isEmpty();
    }

    @Test
    void shouldThrowExceptionWhenSurveyNotFound() {
        // Given
        var id = Instancio.of(Long.class).create();
        // When
        Throwable throwable = Assertions.catchThrowable(() -> surveyService.getSingleSurvey(id));
        // Then
        Assertions.assertThat(throwable)
                .isInstanceOf(SurveyNotFoundException.class)
                .hasMessageContaining(id.toString());
    }


}