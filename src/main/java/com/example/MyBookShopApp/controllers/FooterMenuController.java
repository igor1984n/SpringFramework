package com.example.MyBookShopApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FooterMenuController {

    @GetMapping("/faq")
    public String getFaqPage(){
        return "/faq";
    }

    @GetMapping("/about")
    public String getInfoPage(){
        return "/about";
    }

    @GetMapping("/signin")
    public String getSigninForm(){
        return "/signin";
    }

    @GetMapping("/signup")
    public String createAccount(){
        return "/signup";
    }

    @GetMapping("/contacts")
    public String getContacts(){
        return "/contacts";
    }
}
