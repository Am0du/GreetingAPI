package com.example.task1.apiController;

import com.example.task1.dto.ResponseDTO;
import com.example.task1.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TaskController {
    private final TaskService taskService;
    private final ResponseDTO responseDTO;

    @Autowired
    public TaskController(TaskService taskService, ResponseDTO responseDTO){
        this.taskService = taskService;
        this.responseDTO = responseDTO;
    }
    @GetMapping("/hello")
    public ResponseEntity<?> home (@RequestParam String visitor_name,
                                        HttpServletRequest request){
        if(!visitor_name.isEmpty()) {
            String client = taskService.getClientIp(request);
            Map<String, Object> data = taskService.getData(client);
            String temp = data.get("temp_c").toString().substring(0,2);
            String location = data.get("region").toString();

            responseDTO.setClient_ip(client);
            responseDTO.setLocation(location);
            responseDTO.setGreeting("Hello, "+visitor_name.replace("^\"|\"$", "")+"!," +
                    " the temperature is " + temp + " degrees Celcius in "+ location);
            return ResponseEntity.ok(responseDTO);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("visitor_Name is required");
        }




    }


}
