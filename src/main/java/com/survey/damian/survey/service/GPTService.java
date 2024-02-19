package com.survey.damian.survey.service;

import com.survey.damian.survey.controller.dto.OpenAiRequest;
import com.survey.damian.survey.controller.dto.OpenAiResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class GPTService {

    private final WebClient.Builder webClientBuilder;
    private WebClient webClient;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.version.url}")
    private String apiVersionUrl;
    @Value("${openai.api.key}")
    private String apiKey;

    public GPTService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @PostConstruct
    public void init() {
        this.webClient = webClientBuilder.baseUrl(apiUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .build();
    }

    public Mono<OpenAiResponse> sendRequest(OpenAiRequest request) {
        return webClient.post()
                .uri(apiVersionUrl)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(OpenAiResponse.class);
    }

}