package com.example.MyBookShopApp.data.DTO;

public class Author implements Comparable<Author>{

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Author a) {
        return getName().compareTo(a.getName());
    }
}
