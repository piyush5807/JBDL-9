package com.example.userservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Value("${wallet.amount.default}")
    int default_wallet;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    // TODO: add a listener annotation here
    @KafkaListener(topics = {"wallet_create"}, groupId = "test123")
    public void createWallet(String walletRequest) throws JsonProcessingException {
        JSONObject walletCreateRequest = objectMapper.readValue(walletRequest, JSONObject.class);
        Wallet wallet = Wallet.builder().userId((String)walletCreateRequest.get("userId")).balance(default_wallet).build();
        walletRepository.save(wallet);
    }

    @KafkaListener(topics = {"wallet_update"}, groupId = "test123")
    public void updateWallet(String req) throws JsonProcessingException {
        JSONObject request = objectMapper.readValue(req, JSONObject.class); // non lossy conversion
        // caching part you can add
        Wallet fromWallet = walletRepository.findWalletByUserId((String)request.get("fromUser"));

        // update the cache
        Wallet toWallet = walletRepository.findWalletByUserId((String)request.get("toUser"));
        String transactionId = (String)request.get("transactionId");

        Integer amount =  (Integer) request.get("amount");
//        fromWallet.setBalance(fromWallet.getBalance()-amount);
//
//        toWallet.setBalance(toWallet.getBalance()+amount);

        JSONObject transactionUpdateRequest = new JSONObject();
        if(fromWallet.getBalance() - amount < 0.0){
            transactionUpdateRequest.put("transactionId", transactionId);
            transactionUpdateRequest.put("status", "REJECTED");
            //send it back
            kafkaTemplate.send("transaction_update",transactionId,objectMapper.writeValueAsString(transactionUpdateRequest));
            return;

        }

//        walletRepository.save(fromWallet);
//        walletRepository.save(toWallet);

        walletRepository.updateWallet(fromWallet.getUserId(), 0 - amount);
        walletRepository.updateWallet(toWallet.getUserId(), amount);

        transactionUpdateRequest = new JSONObject();
        transactionUpdateRequest.put("transactionId", transactionId);
        transactionUpdateRequest.put("status", "APPROVED");

        //send it back
        kafkaTemplate.send("transaction_update",transactionId, objectMapper.writeValueAsString(transactionUpdateRequest));
    }
}
