package com.example.notificationservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    SimpleMailMessage simpleMailMessage;

    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = {"send_email"}, groupId = "test123")
    public void send(String req) throws JsonProcessingException {
        JSONObject emailRequest = objectMapper.readValue(req, JSONObject.class);
        simpleMailMessage.setText((String)emailRequest.get("message"));
        simpleMailMessage.setTo((String)emailRequest.get("to"));
        simpleMailMessage.setSubject("Transaction Report");
        simpleMailMessage.setFrom("gfg.geeks.test@gmail.com");
        javaMailSender.send(simpleMailMessage);
    }
}

