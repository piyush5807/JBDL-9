package com.example.userservice;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WalletCreateRequest {

    private String user;
}
