package com.example.jbdl9.demomongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/get_users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/insert_user")
    public void saveUser(@RequestBody User user){
        userRepository.save(user);
    }

    // 45 , 70

    @GetMapping("/get_user_by_age")
    public List<User> getUsersByAge(@RequestParam("min") int min, @RequestParam("max") int max){
        return userRepository.getUsersByAge(min, max);
    }

}
