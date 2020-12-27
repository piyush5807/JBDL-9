package com.example.jbdl9.demoredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class HashController {

    // 1. hashKey                person_name: person object
    // 2. hashKey:person_name    person_name field: name of the person, age field: age of the person

    private static final String hashPrefix = "hash_Person:" ;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/hmset")
    public void hmSet(@RequestBody Person person){
        Map<String, Object> map = new HashMap<>();
        map.put("name", person.getName());
        map.put("age", person.getAge());
        redisTemplate.opsForHash().putAll(hashPrefix + person.getName(), map);
    }

    @GetMapping("/hgetall")
    public Map<Object, Object> hGetAll(@RequestParam("name") String name) {

        Collection<Object> fields = Arrays.asList(Person.NAME, Person.AGE);
        List<Object> data = redisTemplate.opsForHash().multiGet(hashPrefix + name, fields);
//        return data.stream().map(v -> (String) v).collect(Collectors.toList());

//        return Person.convert(data, fields);

        return redisTemplate.opsForHash().entries(hashPrefix + name);
    }

}

// Martina 55