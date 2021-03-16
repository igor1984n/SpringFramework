package org.example.web.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class Book {

    @NotEmpty
    private String author;
    @NotEmpty
    private String title;
    @NotNull
    @Digits(integer = 4, fraction = 0)
    private Integer size;
    private Integer id;

    public Book() {
    }

    public Book(String author, String title, Integer size, Integer id) {
        this.author = author;
        this.title = title;
        this.size = size;
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", size=" + size +
                ", id=" + id +
                '}';
    }
}
