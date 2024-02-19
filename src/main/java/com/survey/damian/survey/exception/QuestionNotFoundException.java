package com.survey.damian.survey.exception;

public class QuestionNotFoundException extends ResourcesNotFoundException {
    public QuestionNotFoundException(Long id) {
        super("Question with id " + id + " not found.");
    }
}
