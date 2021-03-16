package org.example.web.dto;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class BookIdToRemove {

    @NotNull
    @Digits(integer = Integer.MAX_VALUE, fraction = 1)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
