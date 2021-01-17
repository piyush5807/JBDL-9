package com.gfg.majorproject;
import com.gfg.majorproject.user.User;
import com.gfg.majorproject.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class MajorprojectjdblApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(MajorprojectjdblApplication.class, args);
    }

    @Override
    public void run(String...args){
//        Optional<User> user = userRepository.findUserByUserId("abc");
    }
}
