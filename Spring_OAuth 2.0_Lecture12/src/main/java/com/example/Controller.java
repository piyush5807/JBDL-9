package com.example;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    @RequestMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        Map<String, Object> answer = new HashMap<>();
        answer.put("my_name", principal.getAttribute("name"));
        answer.put("my_company", principal.getAttribute("company"));
        return answer;
//		return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    @RequestMapping("/user_details")
    public OAuth2User getUserDetails(@AuthenticationPrincipal OAuth2User principal){
        return principal;
    }

    @RequestMapping("/signin")
    public void signIn(){
        System.out.println("In the sign in function");
    }
}
