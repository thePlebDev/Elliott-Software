package com.elliott.software.controllers;

import com.elliott.software.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("subscribe")
public class Subscription {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("")
    public String subscribe(@RequestParam String id){
        Boolean customerExists = subscriptionService.checkIfCustomerIdExists(id);

        if(!customerExists){
            return "redirect:/signup";
        }

        return "subscribe";
    }
}
