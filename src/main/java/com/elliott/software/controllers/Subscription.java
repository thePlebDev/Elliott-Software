package com.elliott.software.controllers;

import com.elliott.software.service.SubscriptionService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("subscribe")
public class Subscription {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("")
    public String subscribe(@RequestParam String id, Model model) throws StripeException {
        Boolean customerExists = subscriptionService.checkIfCustomerIdExists(id);

        if(!customerExists){
            return "redirect:/signup";
        }
       String clientSecret = subscriptionService.createSubscription(id);
        model.addAttribute("clientSecret",clientSecret);


        return "subscribe";
    }

    @GetMapping("/thankYou")
    public String thankYou(){
        return "thankYou";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(HttpServletRequest req, Exception ex,Model model) {
        model.addAttribute("stripeError",
                "Error occurred. Your card was not charged. Please try again");

        return "stripeException";
    }
}
