package com.survey.damian.survey.controller;

import com.survey.damian.survey.controller.dto.AnswerDto;
import com.survey.damian.survey.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answer")
@AllArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public AnswerDto addNewAnswer(@RequestBody AnswerDto dto) {
        return answerService.addNewAnswer(dto);
    }

    @PostMapping("/survey")
    public List<AnswerDto> submitSurveyAnswers(@RequestBody List<AnswerDto> answers) {
        return answerService.submitSurveyAnswers(answers);
    }

}
