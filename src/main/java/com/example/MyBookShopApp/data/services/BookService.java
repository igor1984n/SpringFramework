package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.DTO.Book;
import com.example.MyBookShopApp.util.BookPriceComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
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

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksData() {
        List<Book> books = jdbcTemplate.query("SELECT * FROM books", (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("Id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getString("priceOld"));
            book.setPrice(rs.getString("price"));
            return book;
        });
        return new ArrayList(books);
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
                .sorted(new BookPriceComparator())
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
                .filter(book -> book.getAuthor().matches(pattern.pattern()))
                .peek(book -> logger.info("author " + book.getAuthor() + " matches " + regExp))
                .collect(Collectors.toList());
        searchResults.addAll(titleMatches);
        searchResults.addAll(authorMatches);

        return searchResults;
    }
}
