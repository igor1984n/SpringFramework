package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.UserService;
import org.example.web.dto.LoginForm;
import org.example.web.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrationController {

    private final Logger logger = Logger.getLogger(RegistrationController.class);
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration_page")
    public String register(Model model){
        logger.info("GET /register returns registration_page.html");
        model.addAttribute("loginForm", new LoginForm());
        return "registration_page";
    }

    @PostMapping(value = "/sign_up")
    public String signUp(LoginForm loginForm){
        userService.addUser(new User(loginForm.getUsername(), loginForm.getPassword()));
        logger.info("redirecting to the login page");
        return "redirect:/login";
    }
}
