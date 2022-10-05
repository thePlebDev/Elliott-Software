package com.elliott.software.service;

import com.elliott.software.models.Authority;
import com.elliott.software.models.User;
import com.elliott.software.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveBasicUser(User user){
        Authority basicAuth = new Authority("READ",user);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.addAuthority(basicAuth);
        this.userRepository.save(user);
    }

    public Boolean doesEmailExist(String email, BindingResult bindingResult){
        Boolean emailExists = this.userRepository.findUserByEmail(email).isPresent();
        if(emailExists){
            ObjectError error = new FieldError("email","email","Email already exists");
            bindingResult.addError(error);
            return true;
        }
        return false;
    }
}
