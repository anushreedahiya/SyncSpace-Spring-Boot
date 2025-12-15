package com.syncspace.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

    @Value("${gemini.api.base:https://generativelanguage.googleapis.com}")
    private String geminiBaseUrl;

    @Value("${gemini.api.key:}")
    private String geminiApiKey;

    @Bean
    public WebClient geminiWebClient() {
        HttpClient httpClient = HttpClient.create();
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(geminiBaseUrl)
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(8 * 1024 * 1024))
                        .build())
                .defaultHeaders(headers -> headers.add("X-Goog-Api-Key", geminiApiKey))
                .build();
    }
}