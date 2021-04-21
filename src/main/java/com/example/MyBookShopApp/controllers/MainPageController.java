package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.DTO.Book;
import com.example.MyBookShopApp.data.services.AuthorService;
import com.example.MyBookShopApp.data.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainPageController {

    private final BookService bookService;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> getRecommendedBooks() {
        return bookService.getBooksData();
    }

    @ModelAttribute("recentBooks")
    public List<Book> getRecentBooks() {
        return bookService.getRecentBooks();
    }

    @ModelAttribute("popularBooks")
    public List<Book> getPopularBooks(){
        return bookService.getPopularBooks();
    }


    @GetMapping("/")
    public String getMainPage() {
        return "index";
    }

    @GetMapping("/popular")
    public String getPopular(){
        return "/books/popular";
    }

    @GetMapping("/recent")
    public String getRecent(){
        return "/books/recent";
    }
}
