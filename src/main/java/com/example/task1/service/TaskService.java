package com.example.task1.service;

import com.example.task1.webC.WebCReq;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TaskService {

    private WebCReq webCReq;

    @Autowired
    public TaskService(WebCReq webCReq){
        this.webCReq = webCReq;

    }
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    public String getClientIp(HttpServletRequest request) {
        String clientIp;
        String xForwardedFor = request.getHeader("X-Forwarded-For");

        clientIp = request.getRemoteAddr();

        return clientIp;

    }

    public String getLocation(String clientIp){
        String city = webCReq.getLocation(clientIp).block().substring(9,14);
        return city;

    }

    public Map<String, Object> getData(String clientIp){
        return webCReq.getData(clientIp).block();

    }

}
