package com.example.jbdl9.mysqldemo;

import java.util.Date;

// ORM - Object relation mapping
public class User {

    private int userId;
    private String name;
    private int age;
    private String country;
    private Date timeOfInsertion;

    public User(int userId, String name, int age, String country, Date timeOfInsertion) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.timeOfInsertion = timeOfInsertion;
        this.userId = userId;

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getTimeOfInsertion() {
        return timeOfInsertion;
    }

    public void setTimeOfInsertion(Date timeOfInsertion) {
        this.timeOfInsertion = timeOfInsertion;
    }
}
