package cn.zko0.remotelabel.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LabelPortController {
    
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/getPort")
    public String getPort(){
        return "nacos registry, serverPort: " + serverPort;
    }
 
}