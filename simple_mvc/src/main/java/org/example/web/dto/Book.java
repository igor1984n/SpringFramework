package org.example.web.dto;

public class Book {

    private String author;
    private String title;
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
