package com.example.config;

import io.netty.channel.ChannelOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        ConnectionProvider provider = ConnectionProvider.builder("test-name").maxConnections(5000)
                                                        .pendingAcquireTimeout(Duration.ofSeconds(10))
                                                        .maxIdleTime(Duration.ofSeconds(10))
                                                        .maxLifeTime(Duration.ofSeconds(10))
                                                        .evictInBackground(Duration.ofSeconds(10)).build();

        HttpClient httpClient = HttpClient.create(provider).responseTimeout(Duration.ofSeconds(10))
                                          .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);

        return WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com")
                        .clientConnector(new ReactorClientHttpConnector(httpClient))
                        .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(4 * 1024 * 1024))
                        .build();
    }
}
