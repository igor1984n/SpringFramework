package com.example.MyBookShopApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenresController {

    @GetMapping("/genres")
    public String getGenres(){
        return "/genres/index";
    }

    @GetMapping("/genres/slug")
    public String getBooksByGenre(){
        return "/genres/slug";
    }
}
