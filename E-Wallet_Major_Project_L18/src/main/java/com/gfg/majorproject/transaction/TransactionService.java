package com.gfg.majorproject.transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.majorproject.notification.EmailRequest;
import com.gfg.majorproject.user.User;
import com.gfg.majorproject.wallet.WalletRequest;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    ObjectMapper objectMapper = new ObjectMapper();
    RestTemplate restTemplate = new RestTemplate();

    public void createTransaction(TransactionRequest transactionRequest) throws JsonProcessingException {
        //get user details
//        URI uri = URI.create("http://localhost:8080/user/"+transactionRequest.getFromUser());
//        HttpHeaders httpHeaders = new HttpHeaders();
//        HttpEntity httpEntity = new HttpEntity(httpHeaders);
//        User fromUser = restTemplate.exchange(uri, HttpMethod.GET, httpEntity,User.class).getBody();
//        User toUser = restTemplate.exchange(uri, HttpMethod.GET, httpEntity,User.class).getBody();

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

        // redis cache save


        //publish event to wallet
        WalletRequest walletRequest = WalletRequest.builder()
                .fromUser(transactionRequest.getFromUser())
                .toUser(transactionRequest.getToUser())
                .amount(transactionRequest.getAmount())
                .transactionId(transaction.getExternalId()) // very important **
                .build();
        kafkaTemplate.send("wallet","wallet", objectMapper.writeValueAsString(walletRequest));
        log.info("sent a msg to topic {}", "wallet");
    }

    @KafkaListener(topics = {"transactionUpdate"}, groupId = "test123")
    public void updateTransaction(String request) throws JsonProcessingException {
        TransactionUpdateRequest updateRequest = objectMapper.readValue(request,TransactionUpdateRequest.class);
        Transaction transaction = transactionRepository.findByExternalId(updateRequest.getTransactionId())
                .get();
        transaction.setStatus(TransactionStatus.valueOf(updateRequest.getStatus().toUpperCase()).toString());
        transactionRepository.save(transaction);
        //Notify sender
        URI uri = URI.create("http://localhost:8080/user/"+transaction.getFromUser());
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        User user = restTemplate.exchange(uri, HttpMethod.GET, httpEntity,User.class).getBody();

        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(user.getEmail());
        emailRequest.setMessage(String.format("Hi %s Your transactionId %s got %s . Sent %s money to %s user",
                user.getUserId(),transaction.getExternalId(),transaction.getStatus(), transaction.getAmount().toString(), transaction.getToUser()));
        kafkaTemplate.send("email",objectMapper.writeValueAsString(emailRequest));

        // Notify receiver

        if(updateRequest.getStatus().toUpperCase().toString().equals(TransactionStatus.APPROVED.toString())) {
            uri = URI.create("http://localhost:8080/user/" + transaction.getToUser());
            httpHeaders = new HttpHeaders();
            httpEntity = new HttpEntity(httpHeaders);
            User receiver = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, User.class).getBody();

            emailRequest = new EmailRequest();
            emailRequest.setTo(receiver.getEmail());
            emailRequest.setMessage(String.format("Hi %s you received  %s money from %s ", receiver.getUserId(),
                    transaction.getAmount(), transaction.getFromUser()));

            kafkaTemplate.send("email", objectMapper.writeValueAsString(emailRequest));
        }
    }
}
