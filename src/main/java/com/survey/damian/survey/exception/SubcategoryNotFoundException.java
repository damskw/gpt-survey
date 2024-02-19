package com.survey.damian.survey.exception;

public class SubcategoryNotFoundException extends ResourcesNotFoundException {
    public SubcategoryNotFoundException(Long id) {
        super("Subcategory with id " + id + " not found.");
    }
}
