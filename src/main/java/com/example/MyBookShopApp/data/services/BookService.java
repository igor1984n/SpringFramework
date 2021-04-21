package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.DAO.BookRepository;
import com.example.MyBookShopApp.data.DTO.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class BookService {

    private Logger logger = Logger.getLogger(BookService.class.getName());

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooksData() {
        return bookRepository.findAll();
    }

    public List<Book> getRecentBooks() {
        return getBooksData()
                .stream()
                .sorted(Comparator.comparing(Book::getId))
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }

    public List<Book> getPopularBooks(){
        return getBooksData()
                .stream()
                .sorted(Comparator.comparing(Book::getPrice))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByRegExp(String regExp) {
        List<Book> searchResults = new ArrayList<>();
        Pattern pattern = Pattern.compile(".*" + regExp + ".*");

        List<Book> titleMatches = getBooksData().stream()
                .filter(book -> book.getTitle().matches(pattern.pattern()))
                .peek(book -> logger.info("title " + book.getTitle() + " matches " + regExp))
                .collect(Collectors.toList());
        List <Book> authorMatches = getBooksData().stream()
                .filter(book -> book.getAuthors().toString().matches(pattern.pattern()))
                .peek(book -> logger.info("author " + book.getAuthors().toString() + " matches " + regExp))
                .collect(Collectors.toList());
        searchResults.addAll(titleMatches);
        searchResults.addAll(authorMatches);

        return searchResults;
    }


}
