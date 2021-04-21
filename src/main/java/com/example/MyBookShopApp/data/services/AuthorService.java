package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.DAO.AuthorRepository;
import com.example.MyBookShopApp.data.DTO.Author;
import com.example.MyBookShopApp.data.DTO.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Map<String, List<Author>> getAuthorsMap() {

        List<Author> authors = authorRepository.findAll();

        return authors.stream().collect(Collectors.groupingBy((Author a) ->  a.getName().substring(0, 1)
        ));
    }
}
