package com.elliott.software.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("subscribe")
public class Subscription {

    @GetMapping
    public String subscribe(){
        return "subscribe";
    }
}
