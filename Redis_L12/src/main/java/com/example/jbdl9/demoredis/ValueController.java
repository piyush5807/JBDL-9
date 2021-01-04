package com.example.jbdl9.demoredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@RestController
public class ValueController {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/get")
    public Object getValue(@RequestParam("key") String key){
        return redisTemplate.opsForValue().get(key);
    }

    @PostMapping("/set")
    public void setValue(@RequestParam("key") String key, @RequestParam("value") String value,
                         @RequestParam(value = "expiry", required = false) Integer expiry){

        if(expiry != null){
            redisTemplate.opsForValue().set(key, value, Duration.ofSeconds((long)expiry));
        }else{
            redisTemplate.opsForValue().set(key, value);
        }
    }
}
