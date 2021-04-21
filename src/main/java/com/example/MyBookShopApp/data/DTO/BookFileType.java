package com.example.MyBookShopApp.data.DTO;

import javax.persistence.*;

@Entity
@Table(name = "book_file_types")
public class BookFileType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormatName name;

    @Column(columnDefinition="TEXT")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FormatName getName() {
        return name;
    }

    public void setName(FormatName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
