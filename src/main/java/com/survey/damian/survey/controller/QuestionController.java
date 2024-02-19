package com.survey.damian.survey.controller;

import com.survey.damian.survey.controller.dto.QuestionDto;
import com.survey.damian.survey.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/question")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public QuestionDto addNewQuestion(@RequestBody QuestionDto dto) {
        return questionService.addNewQuestion(dto);
    }

    @GetMapping(params = "surveyId")
    public List<QuestionDto> getQuestionsForSurvey(@RequestParam Long surveyId) {
        return questionService.getQuestionsForSurvey(surveyId);
    }

}
