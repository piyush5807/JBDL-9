package com.gfg.majorproject.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void create(UserRequest userRequest){
        //get user details
        userRepository.save(User.builder().email(userRequest.getEmail()).userId(userRequest.getUserId()).build());
    }

    public User get(String userId){
        User user =  userRepository.findUserByUserId(userId).orElseThrow(()->new NoSuchElementException(String.format("%s not found",userId )));
        return user;
    }
}
