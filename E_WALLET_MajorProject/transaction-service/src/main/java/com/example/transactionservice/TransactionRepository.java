package com.example.transactionservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Optional<Transaction> findByExternalId(String externalID);
}
