package com.example.dacn.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class SecurityConfig {
    @Bean
    UserDetailsManager users(DataSource dataSource) throws SQLException {
        return new JdbcUserDetailsManager(dataSource);
    }

//    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .formLogin()
                .loginPage("/auth/login")
                .permitAll();
        return http.build();
    }
}
