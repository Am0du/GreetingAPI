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


        public Mono<Double> getTemp(String city) {
            return webClient.get()
                    .uri("http://api.weatherapi.com/v1/current.json?q="+city+"&key="+key)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .map(responseMap -> {
                        Map<String, Object> current = (Map<String, Object>) responseMap.get("current");
                        return Double.valueOf(current.get("temp_c").toString());
                    });
        }
    }



