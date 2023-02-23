package cn.zko0.remotelabel.controller;

import cn.zko0.remotelabel.entity.User;
import cn.zko0.remotelabel.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesePort {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private UserMapper mapper;

    @GetMapping("")
    public String getPort(){
        return serverPort;
    }

}