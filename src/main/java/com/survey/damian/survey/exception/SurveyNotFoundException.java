package com.survey.damian.survey.exception;

public class SurveyNotFoundException extends ResourcesNotFoundException {
    public SurveyNotFoundException(Long id) {
        super("Survey with id " + id + " not found.");
    }
}
