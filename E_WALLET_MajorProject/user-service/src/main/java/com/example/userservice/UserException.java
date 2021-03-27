package com.example.userservice;

public class UserException extends Exception{

    public UserException(String msg) {
        super(msg);
    }

    public UserException() {
        super("Some exception occurred");
    }
}
