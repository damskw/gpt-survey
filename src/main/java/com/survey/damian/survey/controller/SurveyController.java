package com.survey.damian.survey.controller;

import com.survey.damian.survey.controller.dto.SurveyAnalysis;
import com.survey.damian.survey.controller.dto.SurveyDto;
import com.survey.damian.survey.service.SurveyService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/survey")
@AllArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SurveyDto> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/{id}")
    public SurveyDto getSingleSurvey(@PathVariable Long id) {
        return surveyService.getSingleSurvey(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public SurveyDto addNewSurvey(@RequestBody SurveyDto dto) {
        return surveyService.createNewSurvey(dto);
    }

    @GetMapping(path = "/analyse", params = "id")
    public SurveyAnalysis analyseSurvey(@RequestParam Long id) {
        return surveyService.analyseSurvey(id);
    }

}
