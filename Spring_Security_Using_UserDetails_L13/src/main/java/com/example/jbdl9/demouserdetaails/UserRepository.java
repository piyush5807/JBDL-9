package com.example.jbdl9.demouserdetaails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<MyUser, Integer> {

    @Query("select u from MyUser u where u.username=:val")
    MyUser getUser(String val);
}
