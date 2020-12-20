package com.example.jbdl9.demojpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Integer> {

    @Query("select b from Bank b where b.bankId=:bankId")
    public Bank findByBanksId2(int bankId);


    @Query(value = "select * from bank b where b.bank_id=:bankId", nativeQuery = true)
    public Bank findByBanksId(int bankId);

    @Query("select b from Bank b")
    public List<Bank> getAllBanks();

    // 1. JPQL - Java Persistence Query Language
    // 2. Native SQL Query - syntax of SQL is followed
}
