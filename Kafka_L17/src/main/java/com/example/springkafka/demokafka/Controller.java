package com.example.springkafka.demokafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private final String topic = "kafka-test";

    @PostMapping("/produce")
    public void produceMsg(@RequestParam("msg") String msg, @RequestParam("country") String country){
        this.kafkaTemplate.send(topic, country, msg);

    }

    @KafkaListener(topics = {"KAFKA_TOPIC", "kafka-test"})
    public String consume(String msg){
        //
        System.out.println(" the msg being consumed here is " + msg);
        return msg;
    }
}
