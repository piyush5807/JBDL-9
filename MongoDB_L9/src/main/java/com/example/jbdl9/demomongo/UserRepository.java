package com.example.jbdl9.demomongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Integer> {

    @Query("{ age : {$gt : ?0, $lt: ?1}}")
    public List<User> getUsersByAge(int min, int max);

    public List<User> findUserByPhone(String phone);
}
