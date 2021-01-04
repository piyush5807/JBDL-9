package com.example.jbdl9.demouserdetaails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class MyUser implements UserDetails {

    private static final String DELIMITER = ":";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;
//    private String email;
//    private String mobile;
    private String password;
    private String authorities; // auth1:auth2



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Arrays.stream(this.authorities.split(DELIMITER))
                .map(x -> new SimpleGrantedAuthority(x))
                .collect(Collectors.toList());

//        List<GrantedAuthority> authoritiesToReturn = new ArrayList<>();
//        String[]arr = this.authorities.split(DELIMITER);
//        for(String str : arr){
//            authoritiesToReturn.add(new SimpleGrantedAuthority(str));
//        }
//
//        return authoritiesToReturn;
    }



    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
