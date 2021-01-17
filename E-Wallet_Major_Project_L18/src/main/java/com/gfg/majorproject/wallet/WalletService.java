package com.gfg.majorproject.wallet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.majorproject.transaction.TransactionStatus;
import com.gfg.majorproject.transaction.TransactionUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @KafkaListener(topics = {"wallet"},groupId = "test123")
    @Transactional
    public void upsert(String req) throws JsonProcessingException {
        WalletRequest request = objectMapper.readValue(req,WalletRequest.class); // non lossy conversion
        // caching part you can add
        Wallet fromWallet = walletRepository.findWalletByUserId(request.fromUser)
                .orElse(Wallet.builder().balance(0.0).userId(request.fromUser).build());

        // update the cache
        Wallet toWallet = walletRepository.findWalletByUserId(request.toUser)
                .orElse(Wallet.builder().balance(0.0).userId(request.toUser).build());
        String transactionId = request.getTransactionId();

        Double amount =  request.getAmount();
//        fromWallet.setBalance(fromWallet.getBalance()-amount);
//
//        toWallet.setBalance(toWallet.getBalance()+amount);

        if(fromWallet.getBalance() - amount < 0.0){
            TransactionUpdateRequest transactionUpdateRequest = TransactionUpdateRequest
                            .builder().transactionId(transactionId).status(TransactionStatus.REJECTED.toString())
                            .build();
            //send it back
            kafkaTemplate.send("transactionUpdate","transactionUpdate",objectMapper.writeValueAsString(transactionUpdateRequest));
            return;

        }

//        walletRepository.save(fromWallet);
//        walletRepository.save(toWallet);

        walletRepository.updateWallet(fromWallet.getUserId(), 0 - amount);
        walletRepository.updateWallet(toWallet.getUserId(), amount);

        TransactionUpdateRequest transactionUpdateRequest = TransactionUpdateRequest
                .builder().transactionId(transactionId).status(TransactionStatus.APPROVED.toString())
                .build();
        //send it back
        kafkaTemplate.send("transactionUpdate","transactionUpdate", objectMapper.writeValueAsString(transactionUpdateRequest));
    }
}
