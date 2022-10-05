package com.elliott.software.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class RestStripeConfig {
    @Value("${stripe.api.key.public}")
    private String stripeApiKeyPublic;

    @GetMapping("/config")// this is what sets up the default stripe form
    public Map<String, Object> configStripe(){


        Map<String, Object> responseData = new HashMap<>();
        String stripeKey = stripeApiKeyPublic;
        responseData.put("publishableKey",stripeKey);
        return responseData;
    }
}