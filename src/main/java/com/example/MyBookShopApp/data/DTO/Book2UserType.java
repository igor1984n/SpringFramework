package com.example.MyBookShopApp.data.DTO;

import javax.persistence.*;

@Entity
@Table(name = "book2user_type")
public class Book2UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookType name;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private BookType code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookType getName() {
        return name;
    }

    public void setName(BookType name) {
        this.name = name;
    }

    public BookType getCode() {
        return code;
    }

    public void setCode(BookType code) {
        this.code = code;
    }
}
