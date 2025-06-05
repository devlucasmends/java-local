package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service
public class WebClientService {
    @Autowired
    private WebClient webClient;

    public String fetch() {
        try {
            return webClient.get()
                            .uri("https://httpstat.us/200?sleep=5000")
                            .retrieve()
                            .bodyToMono(String.class).timeout(Duration.ofSeconds(2))
                            .block();
        } catch (Exception e) {
            e.printStackTrace();
            return "Timeout ou erro";
        }
    }
}
