package com.example.userservice;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private User user;
    private int status;
    private String email;
}
