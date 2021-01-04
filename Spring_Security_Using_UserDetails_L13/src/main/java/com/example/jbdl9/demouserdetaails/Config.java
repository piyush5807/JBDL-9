package com.example.jbdl9.demouserdetaails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config extends WebSecurityConfigurerAdapter {

    private static final String AUTH1 = "auth1";
    private static final String AUTH2 = "auth2";

    @Autowired
    MyUserService userService;

    // authentication purpose
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("In Config: configure Authentication function");
        auth.userDetailsService(userService);

    }

    // 1. Give your user multiple authorities and bound your APIs to only one of the authority
    // 2. Give your user only 1 authority and make your APIs bound to multiple authorities

    // authorization purpose
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("In Config: configure Authorization function");
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/check_auth1/**").hasAuthority(AUTH1)
                .antMatchers("/check_auth2/**").hasAuthority(AUTH2)
                .antMatchers("/**").permitAll()
                .and().formLogin();
    }


    @Bean
    public PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }
}
