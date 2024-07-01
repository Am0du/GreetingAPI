package com.example.task1.webC;

import com.example.task1.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class WebCReq {
    private final WebClient webClient;
    private ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(WebCReq.class);
    @Value("${apiKey}")
    private String key;

    @Autowired
    public WebCReq(WebClient.Builder webClient, ObjectMapper objectMapper) {
        this.webClient = webClient.build();
        this.objectMapper = objectMapper;
    }

    public Mono<String> getLocation(String clientIp){
        return webClient.get()
                .uri("http://ip-api.com/json/"+clientIp+"?fields=city")
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<Map<String, Object>> getData(String clientIp) {

    return webClient.get()
            .uri("http://api.weatherapi.com/v1/current.json?q="+clientIp+"&key="+key)
            .retrieve()
            .bodyToMono(Map.class)
            .map(this::extractRegionAndTemp);
    }

    private Map<String, Object> extractRegionAndTemp(Map<String, Object> response) {
        Map<String, Object> location = (Map<String, Object>) response.get("location");
        Map<String, Object> current = (Map<String, Object>) response.get("current");

        String region = (String) location.get("region");
        Double tempC = (Double) current.get("temp_c");

        return Map.of(
                "region", region,
                "temp_c", tempC
        );
    }

}




