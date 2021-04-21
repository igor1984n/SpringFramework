package com.example.MyBookShopApp.data.DTO;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String hash;
    @Column(nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date regTime;
    @Column(nullable = false)
    @ColumnDefault("0")
    private BigDecimal balance;
    private String name;

    @OneToMany(mappedBy = "user")
    private Set<BookReview> bookReviews = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<BookReviewLike> bookReviewLikes = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Message> messages = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<FileDownload>fileDownloads = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<BalanceTransaction>transactions = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserContact userContact;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book2user",
    joinColumns = {@JoinColumn(name = "user_id")},
    inverseJoinColumns = {@JoinColumn(name = "book_id")})
    private List<Book>books;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BookReview> getBookReviews() {
        return bookReviews;
    }

    public void setBookReviews(Set<BookReview> bookReviews) {
        this.bookReviews = bookReviews;
    }

    public Set<BookReviewLike> getBookReviewLikes() {
        return bookReviewLikes;
    }

    public void setBookReviewLikes(Set<BookReviewLike> bookReviewLikes) {
        this.bookReviewLikes = bookReviewLikes;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<FileDownload> getFileDownloads() {
        return fileDownloads;
    }

    public void setFileDownloads(Set<FileDownload> fileDownloads) {
        this.fileDownloads = fileDownloads;
    }

    public Set<BalanceTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<BalanceTransaction> transactions) {
        this.transactions = transactions;
    }

    public UserContact getUserContact() {
        return userContact;
    }

    public void setUserContact(UserContact userContact) {
        this.userContact = userContact;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
