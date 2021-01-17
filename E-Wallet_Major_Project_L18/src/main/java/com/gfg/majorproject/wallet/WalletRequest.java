package com.gfg.majorproject.wallet;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WalletRequest {
    String transactionId;
    String fromUser;
    String toUser;
    Double amount;
}
