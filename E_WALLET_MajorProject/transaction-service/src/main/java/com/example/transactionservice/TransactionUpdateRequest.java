package com.example.transactionservice;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransactionUpdateRequest {
    private String transactionId;
    private String status;
}
