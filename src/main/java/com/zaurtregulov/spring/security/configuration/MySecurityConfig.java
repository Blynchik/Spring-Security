package com.zaurtregulov.spring.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/").hasAnyRole("EMPLOYEE", "HR", "MANAGER")
                .requestMatchers("/hr_info").hasRole("HR")
                .requestMatchers("/manager_info/**").hasRole("MANAGER")
                .anyRequest().authenticated()
                .and().formLogin().permitAll();

        return http.build();
    }
}