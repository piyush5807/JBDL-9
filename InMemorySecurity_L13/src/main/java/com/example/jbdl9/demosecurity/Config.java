package com.example.jbdl9.demosecurity;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config extends WebSecurityConfigurerAdapter {

    private static final String AUTH1 = "auth1";
    private static final String AUTH2 = "auth2";

    // authentication purpose
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("In Config: configure Authentication function");
        auth.inMemoryAuthentication()
                .withUser("piyush")
                .password("piyush123")
                .authorities(AUTH1)
                .and()
                .withUser("raj")
                .password("raj123")
                .authorities(AUTH2, AUTH1);

    }

    // 1. Give your user multiple authorities and bound your APIs to only one of the authority
    // 2. Give your user only 1 authority and make your APIs bound to multiple authorities

    // authorization purpose
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("In Config: configure Authorization function");
        http.
                httpBasic().and()
                .authorizeRequests()
                .antMatchers("/check_auth1/**").hasAuthority(AUTH1)
                .antMatchers("/check_auth2/**").hasAuthority(AUTH2)
                .antMatchers("/**").permitAll()
                .and().formLogin();
    }


    @Bean
    public PasswordEncoder getPE(){
        System.out.println("In Config: getPE function");
        return NoOpPasswordEncoder.getInstance();
    }
}
