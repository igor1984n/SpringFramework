package com.example.MyBookShopApp.data.DTO;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "books")
public class Book implements Comparable<Book> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Transient
    private BigDecimal priceOld;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false, columnDefinition = "DATE")
    private Date pubDate;
    @Column(nullable = false, columnDefinition = "BOOLEAN")
    private boolean isBestseller;
    @Column(nullable = false)
    private String slug;
    private String image;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false, columnDefinition = "SMALLINT")
    @ColumnDefault("0")
    private Integer discount;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book2author",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")}
    )
    private List<Author> authors;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book2genre",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")}
    )
    private List<Genre>genres;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book2user",
    joinColumns = {@JoinColumn(name = "book_id")},
    inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User>users;

    @OneToMany(mappedBy = "book")
    private List<BookReview> bookReviewList = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private Set<FileDownload> fileDownloads = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<BalanceTransaction> transactions = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPriceOld() {
        BigDecimal priceOld = price.add(price.multiply(BigDecimal.valueOf(discount)).divide(BigDecimal.valueOf(100)));
        return priceOld;
    }

    public void setPriceOld(BigDecimal priceOld) {
        this.priceOld = priceOld;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public List<BookReview> getBookReviewList() {
        return bookReviewList;
    }

    public void setBookReviewList(List<BookReview> bookReviewList) {
        this.bookReviewList = bookReviewList;
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

    public void setBestseller(boolean bestseller) {
        isBestseller = bestseller;
    }

    public boolean isBestseller() {
        return isBestseller;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public int compareTo(Book b) {
        return getId().compareTo(b.getId());
    }
}
