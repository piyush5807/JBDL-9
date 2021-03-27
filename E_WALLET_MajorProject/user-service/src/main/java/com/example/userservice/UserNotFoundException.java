package com.example.userservice;

public class UserNotFoundException extends Exception{

    public UserNotFoundException() {
        super("User not found");
    }
}
