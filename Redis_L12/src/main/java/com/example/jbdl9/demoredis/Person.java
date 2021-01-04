package com.example.jbdl9.demoredis;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class Person implements Serializable {

    public static final String NAME = "name";
    public static final String AGE = "age";


    private String name;
    private int age;

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

//    public static Person convert(List<Object> data, Collection<String> fields){
//
//        Person person = new Person();
//
//        for(String field : fields){
//            person.
//        }
//    }
}
