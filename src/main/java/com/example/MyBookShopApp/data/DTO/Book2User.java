package com.example.MyBookShopApp.data.DTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book2user")
public class Book2User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date time;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "code", nullable = false)
    private Book2UserType typeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Book2UserType getTypeId() {
        return typeId;
    }

    public void setTypeId(Book2UserType typeId) {
        this.typeId = typeId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
