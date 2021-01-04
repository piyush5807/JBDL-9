package com.example.jbdl9.demouserdetaails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder encoder;

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

    @PostMapping("/signup_auth1")
    public void signup(@RequestBody MyUser user){

//        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        repository.save(user);
    }
}
