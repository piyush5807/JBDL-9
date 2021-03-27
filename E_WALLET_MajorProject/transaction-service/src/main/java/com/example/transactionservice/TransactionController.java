package com.example.transactionservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    public void createTransaction(@RequestBody TransactionRequest transactionRequest) throws JsonProcessingException {
        transactionService.createTransaction(transactionRequest);
    }
}
