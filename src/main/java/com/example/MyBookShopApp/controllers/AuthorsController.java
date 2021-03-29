package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorsController {

    private final AuthorService authorService;

    @Autowired
    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/list")
    public String getAuthors(Model model){
        model.addAttribute("authorData", authorService.getAuthorsData());
        return "/authors/index";
    }
}
