package com.example.jbdl9.restapis;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
//@Scope("prototype")
public class User {

    private int userId;
    private String name;
    private int age;
    private String country;
    private Date timeOfInsertion;

    public User(){
        this(2, "ABC", 12, "India");
        System.out.println("In User Constructor");
        System.out.println(this);
    }

    public User(int userId, String name, int age, String country) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.country = country;
        this.timeOfInsertion = new Date();
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

//    @Override
//    public String toString() {
//        return "User{" +
//                "userId=" + userId +
//                ", name='" + name + '\'' +
//                ", age=" + age +
//                ", country='" + country + '\'' +
//                ", timeOfInsertion=" + timeOfInsertion +
//                '}';
//    }
}
