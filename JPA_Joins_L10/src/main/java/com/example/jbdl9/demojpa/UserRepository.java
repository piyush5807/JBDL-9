package com.example.jbdl9.demojpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.id=:id")
    public User getUserById(int id);

//    @Query("select u from User u where u.country=:country")
    public List<User> findBycountry(String c);

//    public List<User> findByState(String c);

    @Query(value = "select * from User u where u.joinedBankColumn=:id", nativeQuery = true)
    public List<User> getUsersByBankId(int id);

}
