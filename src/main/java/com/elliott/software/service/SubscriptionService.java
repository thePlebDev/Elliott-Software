package com.elliott.software.service;

import com.elliott.software.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    private UserRepository userRepository;

    @Autowired
    public SubscriptionService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public Boolean checkIfCustomerIdExists(String customerId){
        Boolean userFound = this.userRepository.existsByCustomerId(customerId);

        return userFound;
    }
}
