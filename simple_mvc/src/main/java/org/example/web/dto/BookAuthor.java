package org.example.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class BookAuthor {

    @NotEmpty
    @NotBlank
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
