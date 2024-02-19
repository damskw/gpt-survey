package com.survey.damian.survey.controller.dto;

import java.util.List;

public record OpenAiRequest(
        String model,
        List<Message> messages,
        Integer n
) {
    public record Message(
            String role,
            String content
    ) {}
}
