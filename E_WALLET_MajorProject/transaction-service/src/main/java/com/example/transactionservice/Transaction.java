package com.example.transactionservice;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internalId")
    private Long id;
    private String externalId= UUID.randomUUID().toString();
    private String transactionDateTime;
    private String fromUser; // sender
    private int amount;
    private String toUser; // receiver
    private String purpose;
    private String status;
}
