package com.survey.damian.survey.exception;

public abstract class ResourcesNotFoundException extends RuntimeException {

    public ResourcesNotFoundException(String message) {
        super(message);
    }

}
