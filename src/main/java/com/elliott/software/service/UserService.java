package com.elliott.software.service;

import com.elliott.software.models.Authority;
import com.elliott.software.models.User;
import com.elliott.software.repositories.UserRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
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

    public String saveBasicUser(User user) throws StripeException {
        Authority basicAuth = new Authority("READ",user);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.addAuthority(basicAuth);

       String customerId = createCustomer(user.getEmail(),user.getUsername());
       user.setCustomerId(customerId);
        this.userRepository.save(user);
        return customerId;
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
    private String createCustomer(String username, String email) throws StripeException {
        CustomerCreateParams params =
                CustomerCreateParams
                        .builder()
                        .setEmail(email)
                        .setName(username)
                        .build();
        Customer customer = Customer.create(params);
        return customer.getId();

    }

}
