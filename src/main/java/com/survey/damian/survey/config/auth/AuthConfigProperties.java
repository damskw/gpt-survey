package com.survey.damian.survey.config.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.jwt")
public record AuthConfigProperties(
        String secret,
        int validity
) {
}
