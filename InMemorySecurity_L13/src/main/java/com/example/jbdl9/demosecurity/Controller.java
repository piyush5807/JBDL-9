package com.example.jbdl9.demosecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class Controller {

    @GetMapping("/check_auth1")
    public String testFunction(){
        return "Hello auth1 user!!";
    }

    @GetMapping("/check_auth2")
    public String testFunction2(){
        return "Hello auth2 user!!";
    }

    @GetMapping("/")
    public String rootFunction(){
        return "Anybody can access this!!";
    }

}
