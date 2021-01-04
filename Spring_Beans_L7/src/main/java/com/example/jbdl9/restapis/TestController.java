package com.example.jbdl9.restapis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    Config config;

    @GetMapping("/test1")
    public void testAPI(){
        User user = new User();
        System.out.println(config.getUser());
    }
}
