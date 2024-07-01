package com.example.task1.dto;

import org.springframework.stereotype.Service;

@Service
public class ResponseDTO {

    private String client_ip;
    private String location;
    private String greeting;

    public ResponseDTO(){}


    public ResponseDTO( String client_ip) {
        this.client_ip = client_ip;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String clientIp) {
        this.client_ip = clientIp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                ", clientIp='" + client_ip + '\'' +
                ", location='" + location + '\'' +
                ", greeting='" + greeting + '\'' +
                '}';
    }
}
