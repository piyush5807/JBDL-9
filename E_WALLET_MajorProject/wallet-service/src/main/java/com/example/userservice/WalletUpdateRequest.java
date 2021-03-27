package com.example.userservice;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WalletUpdateRequest {

    String transactionId;
    private String toUser;
    private String fromUser;
    private int amount;
}
