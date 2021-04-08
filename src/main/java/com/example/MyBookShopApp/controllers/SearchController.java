package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.DTO.Book;
import com.example.MyBookShopApp.data.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class SearchController {

    private final BookService bookService;

    @Autowired
    public SearchController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/search/{regExp}")
    public String getSearchPage(@PathVariable("regExp") String regExp, Model model) {
        List<Book> books = bookService.getBooksByRegExp(regExp);
        int searchResultSize = 0;
        if (books.size() == 0) {
            books = bookService.getBooksData();
        } else {
            searchResultSize = books.size();
        }
        model.addAttribute("searchResult", books);
        model.addAttribute("searchResultSize", searchResultSize);
        return "/search/index";
    }
}
