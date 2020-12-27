package com.example.jbdl9.demoredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/list/")
public class ListController {

    private static final String listKey = "Person:JBDL9";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/lpush")
    public void lPush(@RequestBody Person person){
        redisTemplate.opsForList().leftPush(listKey, person);
    }

    @PostMapping("/rpush")
    public void rPush(@RequestBody Person person){
        redisTemplate.opsForList().rightPush(listKey, person);
    }

    @PostMapping("/lpop")
    public Person lPop(){
        return (Person) redisTemplate.opsForList().leftPop(listKey);
    }

    @PostMapping("/rpop")
    public Person rPop(){
        return (Person) redisTemplate.opsForList().rightPop(listKey);
    }

    @GetMapping("/lrange")
    public List<Person> lRange(@RequestParam("start") int start, @RequestParam("end") int end){
        List<Object> data = redisTemplate.opsForList().range(listKey, start, end);
        return data.stream().map(v -> (Person)v).collect(Collectors.toList());
    }

}
