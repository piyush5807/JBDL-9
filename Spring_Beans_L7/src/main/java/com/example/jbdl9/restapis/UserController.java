package com.example.jbdl9.restapis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    // 1. Inversion of Control
    // 2. Dependency Injection

    @Autowired
    User user;

    @Autowired
    User user2;

    // 1. GET API -> Used to get info for a user
    // 2. POST API -> Used to add a user to system
    // 3. DELETE API -> Used to delete a user from system
    // 4. PUT API -> Used to change info for a user

    // Spring will create objects for those classes only which have @Component annotation over it either directly or indirectly

    public static HashMap<Integer, User> userInfo = new HashMap<>();

    @Override
    public String toString() {
        return super.toString();
    }

    @GetMapping("/get_user")
    private ResponseEntity<User> getUser(@RequestParam(value = "userId", required = false, defaultValue = "3") int id){
        if(userInfo.containsKey(id)){
            return new ResponseEntity<>(userInfo.get(id), HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/insert_user", method = RequestMethod.POST)
    public void insertUser(@RequestBody User myUser){
        if(userInfo.containsKey(myUser.getUserId())){
            return;
        }

        userInfo.put(myUser.getUserId(), myUser);
    }

    @GetMapping("/get_users")
    public Map<Integer, User> getAllUsers(){
        return userInfo;
    }

    // localhost:8080/delete_user/5/piyush

    @DeleteMapping("/delete_user/{userId}/{name}")
    public void deleteUser(@PathVariable(value = "name") String my_name,
                            @PathVariable(value = "userId") int my_id){
        userInfo.remove(my_id);
    }

    // 1. Client is sending the entire user object - changed + old
    // 2. Client is sending only the values that need to be modified

    // curl -XPUT "127.0.0.1:8080/change_userInfo?userId=1" -H 'Content-Type: application/json' -d'{"country": "USA"}' -v

    @PutMapping("/change_userInfo")
    public User modifyDetails(@RequestParam(value = "userId") int userId, @RequestBody User user){

//        scenario #1 userInfo.put(userId, user);

//        scenario #2
        User prevUser = userInfo.get(userId);

        if(user.getAge() != 0){
            prevUser.setAge(user.getAge());
        }

        if(user.getCountry() != null){
            prevUser.setCountry(user.getCountry());
        }

        if(user.getName() != null){
            prevUser.setName(user.getName());
        }

        userInfo.put(userId, prevUser);

        return prevUser;

    }

    @GetMapping(value = "/test")
    public void testFunction(){
        System.out.println(this.user);
        System.out.println(this.user2);
    }

    @GetMapping("/test3")
    public void testFunction3(){
        System.out.println("Printing Hello");
    }

    // In User Constructor
    //com.example.jbdl9.restapis.User@3e587920

    @GetMapping("/test2")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public User testFunction2(@RequestBody User user){
        System.out.println(user);
        return user;
    }
}
