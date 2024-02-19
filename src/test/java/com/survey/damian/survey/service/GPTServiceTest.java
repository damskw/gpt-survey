package com.survey.damian.survey.service;

import com.survey.damian.survey.controller.dto.OpenAiRequest;
import com.survey.damian.survey.controller.dto.OpenAiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GPTServiceTest {
    @Autowired
    private WebTestClient webTestClient;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.version.url}")
    private String apiVersionUrl;

    @Value("${openai.api.key}")
    private String apiKey;

    @Test
    void testSendRequest() {
        // Given
        var message = new OpenAiRequest.Message("system", "What's the weather in Poland like?");
        var request = new OpenAiRequest("gpt-3.5-turbo", Collections.singletonList(message), 1);

        // When & Then
        webTestClient.post()
                .uri(apiUrl + apiVersionUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(OpenAiResponse.class);
    }
}
