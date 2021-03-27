package com.example.transactionservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RestTemplate restTemplate;


    @Autowired
    KafkaTemplate<String, String>kafkaTemplate;

    public void createTransaction(TransactionRequest transactionRequest) throws JsonProcessingException {

        Transaction transaction = Transaction.builder()
                .fromUser(transactionRequest.getFromUser())
                .toUser(transactionRequest.getToUser())
                .amount(transactionRequest.getAmount())
                .purpose(transactionRequest.getPurpose())
                .status(TransactionStatus.PENDING.toString())
                .externalId(UUID.randomUUID().toString())
                .transactionDateTime(new Date().toString())
                .build();
        transactionRepository.save(transaction);

        //publish event to wallet
        JSONObject walletRequest = new JSONObject();
        walletRequest.put("fromUser", transactionRequest.getFromUser());
        walletRequest.put("toUser", transactionRequest.getToUser());
        walletRequest.put("amount", transactionRequest.getAmount());
        walletRequest.put("transactionId", transaction.getExternalId());
        kafkaTemplate.send("wallet_update",transaction.getExternalId(), objectMapper.writeValueAsString(walletRequest));
    }

    @KafkaListener(topics = {"transaction_update"}, groupId = "test123")
    public void updateTransaction(String request) throws JsonProcessingException {
        JSONObject updateRequest = objectMapper.readValue(request, JSONObject.class);
        Transaction transaction = transactionRepository.findByExternalId((String)updateRequest.get("transactionId")).get();
        transaction.setStatus(TransactionStatus.valueOf((String)updateRequest.get("status")).toString());
        transactionRepository.save(transaction);

        //Notify sender
        URI uri = URI.create("http://localhost:7000/user?userId="+transaction.getFromUser());
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        JSONObject user = restTemplate.exchange(uri, HttpMethod.GET, httpEntity,JSONObject.class).getBody();

        JSONObject emailRequest = new JSONObject();
        emailRequest.put("to",user.get("email"));
        emailRequest.put("message", String.format("Hi %s Your transactionId %s got %s . Sent %s money to %s user",
                user.get("userId"),transaction.getExternalId(),transaction.getStatus(), String.valueOf(transaction.getAmount()), transaction.getToUser()));
        kafkaTemplate.send("send_email", (String)user.get("userId"), objectMapper.writeValueAsString(emailRequest));

        // Notify receiver

        String status = (String)updateRequest.get("status");

        if(status.equals(TransactionStatus.APPROVED.toString())) {
            uri = URI.create("http://localhost:7000/user?userId=" + transaction.getToUser());
            httpHeaders = new HttpHeaders();
            httpEntity = new HttpEntity(httpHeaders);
            JSONObject receiver = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, JSONObject.class).getBody();

            emailRequest = new JSONObject();
            emailRequest.put("to", receiver.get("email"));
            emailRequest.put("message", String.format("Hi %s you received  %s money from %s ", receiver.get("userId"),
                    transaction.getAmount(), transaction.getFromUser()));

            kafkaTemplate.send("send_email", (String)user.get("userId"), objectMapper.writeValueAsString(emailRequest));
        }
    }
}
