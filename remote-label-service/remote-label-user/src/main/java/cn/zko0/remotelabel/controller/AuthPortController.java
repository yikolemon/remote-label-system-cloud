//package cn.zko0.remotelabel.controller;
//
//import cn.zko0.remotelabel.entity.User;
//import cn.zko0.remotelabel.mapper.UserMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class AuthPortController {
//
//    @Value("${server.port}")
//    private String serverPort;
//
//    @Autowired
//    private UserMapper mapper;
//
//    @GetMapping("")
//    public String getPort(){
//        User user = new User();
//        user.setUserName("fuck");
//        user.setMobile("1111");
//        user.setPassword("123");
//        mapper.insert(user);
//        return "nacos registry, serverPort: " + serverPort;
//    }
//
//}