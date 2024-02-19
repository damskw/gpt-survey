package com.survey.damian.survey.exception;

public class CategoryNotFoundException extends ResourcesNotFoundException {
    public CategoryNotFoundException(Long id) {
        super("Category with id " + id + " not found.");
    }
}
