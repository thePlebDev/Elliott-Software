package com.elliott.software.security;

import com.elliott.software.security.authenticationProviders.AuthenticatinoProviderService;
import com.elliott.software.security.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {
    @Bean
    public UserDetailsService userDetailsService(){
        return  new CustomUserDetailService();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticatinoProviderService authenticationProvider(){
        return new AuthenticatinoProviderService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authenticationProvider(authenticationProvider());
        http.authorizeRequests()
                .mvcMatchers("/admin/blogPost/create").hasAuthority("ADMIN");


        return http.build();
    }
}
