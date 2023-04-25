package com.zaurtregulov.spring.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("zaur")
                .password(passwordEncoder.encode("zaur"))
                .roles("EMPLOYEE")
                .build());
        manager.createUser(User.withUsername("elena")
                .password(passwordEncoder.encode("elena"))
                .roles("HR")
                .build());
        manager.createUser(User.withUsername("ivan")
                .password(passwordEncoder.encode("ivan"))
                .roles("MANAGER", "HR")
                .build());
        return manager;
    }
}