package com.example.jbdl9.demomongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Queue;

@Repository
public class UserDefRepository {

    @Autowired
    MongoOperations mongoOperations;


    public List<User> getUsersByAge(int min, int max){
        Query query = new Query();
        query.addCriteria(Criteria.where("age").gt(min).lt(max));
        return mongoOperations.find(query, User.class);
    }
}
