package com.zaurtregulov.spring.security.configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;

@EnableWebSecurity
public class MySecurityConfig {

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(userBuilder.username("zaur").password("zaur").roles("employee"))
                .withUser(userBuilder.username("elena").password("elena").roles("hr"))
                .withUser(userBuilder.username("ivan").password("ivan").roles("manager","hr"));
    }
}
