package com.example.MyBookShopApp.data.DTO;

import javax.persistence.*;

@Entity
@Table(name = "book_files")
public class BookFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String hash;
    @Column(nullable = false)
    private String typeId;
    @Column(nullable = false)
    private String path;
}
