package com.example.jbdl9.demojpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") int id){
        return userRepository.getUserById(id);
    }

    @PostMapping("/user")
    public void addUser(@RequestBody User user){

        userRepository.save(user);
    }

    @GetMapping("/users_by_country")
    public List<User> getUsersByCountry(@RequestParam("country") String country){

//        return userRepository.findByCountry(country);
        return null;
    }
}
