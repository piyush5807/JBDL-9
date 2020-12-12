package com.example.jbdl9.restapis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import java.util.Locale;

@Configuration
public class Config{

    @Bean
    @Scope("prototype")
    public User getUser(){
        System.out.println("In Config class: getUser function");
        return new User();
    }

}
