package com.gfg.majorproject.transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction")
    public void create(@RequestBody TransactionRequest transactionRequest){
        try {
            transactionService.createTransaction(transactionRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
