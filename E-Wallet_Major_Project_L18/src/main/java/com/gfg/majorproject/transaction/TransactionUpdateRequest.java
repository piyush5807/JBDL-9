package com.gfg.majorproject.transaction;
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


// update the transaction with id = transactionId and set it's status to status