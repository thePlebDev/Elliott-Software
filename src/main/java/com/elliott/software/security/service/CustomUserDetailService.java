package com.elliott.software.security.service;

import com.elliott.software.models.User;
import com.elliott.software.repositories.UserRepository;
import com.elliott.software.security.models.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class CustomUserDetailService  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s =
                ()-> new UsernameNotFoundException("Problem during authentication");
        User foundUser = userRepository.findUserByUsername(username)
                .orElseThrow(s);
        return new SecurityUser(foundUser);
    }
}
