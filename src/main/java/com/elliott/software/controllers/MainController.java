package com.elliott.software.controllers;

import com.elliott.software.models.User;
import com.elliott.software.repositories.UserRepository;
import com.elliott.software.service.UserService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class MainController {


    @Autowired
    private UserService userService;

    @GetMapping
    public String home(){
        return "home";
    }

    @GetMapping("/signup")
    public String signUp(Model model){
        model.addAttribute("user",new User());
        return "signup";
    }
    @PostMapping("/signup")
    public String signupPost(@Valid User user, BindingResult bindingResult) throws StripeException {

        //check if email already exists
        userService.doesEmailExist(user.getEmail(),bindingResult);

        if(bindingResult.hasErrors()){
            System.out.println("THERE WAS AN ERROR");
            return "signup";
        }

        this.userService.saveBasicUser(user);

        return "signup";
    }
}
