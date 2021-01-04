package com.example.jbdl9.mysqldemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    DbManager manager;

    @GetMapping(value = "/get_users")
    public List<User> getUsers() throws SQLException {

        return manager.getUsers();
    }

    @GetMapping("/get_user/{id}")
    public List<User> getUserInfo(@PathVariable("id") int id) throws SQLException{
        return manager.getUser(id);
    }

    @PostMapping("/insert_user")
    public void insertUser(@RequestBody User user) throws SQLException{
        manager.insertUser(user);
    }
}
