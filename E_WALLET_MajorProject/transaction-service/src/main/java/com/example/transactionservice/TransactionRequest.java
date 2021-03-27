package com.example.transactionservice;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    private String fromUser;
    private int amount;
    private String toUser;
    private String purpose;
}
