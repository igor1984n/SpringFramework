package com.example.MyBookShopApp.util;

import com.example.MyBookShopApp.data.DTO.Book;

import java.util.Comparator;

public class BookPriceComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return Integer.parseInt(b1.getPrice().substring(1).replaceAll("\\D", "")) -
                Integer.parseInt(b2.getPrice().substring(1).replaceAll("\\D", ""));
    }
}
