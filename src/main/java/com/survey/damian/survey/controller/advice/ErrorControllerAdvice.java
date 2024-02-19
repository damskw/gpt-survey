package com.survey.damian.survey.controller.advice;

import com.survey.damian.survey.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(value = {
            CategoryNotFoundException.class,
            SubcategoryNotFoundException.class,
            QuestionNotFoundException.class,
            SurveyNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleNoResource(ResourcesNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(OpenAiServiceException.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    protected ErrorResponse handleServiceException(OpenAiServiceException e) {
        return new ErrorResponse(e.getMessage());
    }


    private record ErrorResponse(
            String errorMessage
    ){
    }

}
