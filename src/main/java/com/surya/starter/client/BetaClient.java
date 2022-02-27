package com.surya.starter.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class BetaClient {

    RestTemplate restTemplate;

    @CircuitBreaker(name = "beta")
    public String getName() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8081/beta/name", String.class);
        return responseEntity.getBody();
    }
}
