package org.example.app.services;

import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final ProjectRepository<Book>bookRepo;

    @Autowired
    public BookService(ProjectRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks(){
        return bookRepo.retrieveAll();
    }

    public void saveBook(Book book){
        bookRepo.store(book);
    }

    public boolean removeBookById(Integer bookIdToRemove){
        return bookRepo.removeItemById(bookIdToRemove);
    }

    public boolean removeBookByTitle(String bookTitleToRemove){
        return bookRepo.removeItemByTitle(bookTitleToRemove);
    }

    public boolean removeBookByAuthor(String bookAuthorToRemove){
        return bookRepo.removeItemsByAuthor(bookAuthorToRemove);
    }

    public boolean removeBookBySize(Integer bookSizeToRemove) {
        return bookRepo.removeItemsBySize(bookSizeToRemove);
    }

    public List<Book> getBookByTitle(String bookTitle) {
        return bookRepo.getItemByTitle(bookTitle);
    }

    public List<Book> getBookByAuthor(String bookAuthor) {
        return bookRepo.getItemByAuthor(bookAuthor);
    }

    public List<Book> getBookBySize(Integer bookSize) {
        return bookRepo.getItemBySize(bookSize);
    }
}
