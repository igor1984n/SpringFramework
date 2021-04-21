package com.example.MyBookShopApp.data.DAO;

import com.example.MyBookShopApp.data.DTO.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    public Author findAuthorById(Integer id);

    public List<Author> findAuthorsByName(String name);

}
