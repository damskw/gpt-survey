package com.survey.damian.survey.controller.dto;

import java.util.List;


public record OpenAiResponse(
        String id,
        String object,
        Long created,
        String model,
        List<Choice> choices,
        Usage usage,
        String systemFingerprint
) {
    public record Choice(
            Integer index,
            Message message,
            Object logprobs,
            String finishReason
    ) {
    }

    public record Message(
            String role,
            String content
    ) {
    }

    public record Usage(
            Integer promptTokens,
            Integer completionTokens,
            Integer totalTokens
    ) {
    }
}

