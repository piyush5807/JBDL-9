package com.gfg.majorproject.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id")String id){
        return userService.get(id);

    }
    @PostMapping("/user")
    public void getUser(@RequestBody UserRequest userRequest){
        userService.create(userRequest);
        return;

    }
}
