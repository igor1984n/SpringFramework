package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value = "/books")
public class BookShelfController {

    private Logger logger = Logger.getLogger(BookShelfController.class);
    private BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        List<Book> bookList = bookService.getAllBooks();
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookList);
        logger.info("got book shelf with the size " + bookList.size());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(Book book) {
        bookService.saveBook(book);
        logger.info("current repository size: " + bookService.getAllBooks().size());
        return "redirect:/books/shelf";
    }

    @PostMapping("/remove")
    public String removeBook(@RequestParam(value = "bookIdToRemove") Integer bookIdToRemove) {
        if (!bookService.removeBookById(bookIdToRemove)) {
            logger.info("cannot find a book with this id: " + bookIdToRemove);
        }
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeByTitle")
    public String removeBookByTitle(@RequestParam(value = "bookTitle") String bookTitle){
        if(!bookService.removeBookByTitle(bookTitle)){
            logger.info("cannot find and remove a book with the title matching this query: " + bookTitle);
        }
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeByAuthor")
    public String removeBookByAuthor(@RequestParam(value = "bookAuthor") String bookAuthor){
        if(!bookService.removeBookByAuthor(bookAuthor)){
            logger.info("cannot find and remove a book by the author matching this query: " + bookAuthor);
        }
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeBySize")
    public String removeBookBySize(@RequestParam(value="bookSize") Integer bookSize){
        if(!bookService.removeBookBySize(bookSize)){
            logger.info("cannot find a book with the specified size: " + bookSize);
        }
        return "redirect:/books/shelf";
    }

    @GetMapping("/findByTitle")
    public  String getByTitle(@RequestParam(value="bookTitle") String bookTitle, Model model){

        List<Book> bookList = bookService.getBookByTitle(bookTitle);
        if(bookList.size() == 0){
            logger.info("cannot get a book with the title matching this query: " + bookTitle);
            return "redirect:/books/shelf";
        }
        logger.info("got " + bookList.size() + " books filtered by title");
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookList);
        return "book_shelf";
    }

    @GetMapping("/findByAuthor")
    public String getByAuthor(@RequestParam(value="bookAuthor") String bookAuthor, Model model){
        List<Book> bookList = bookService.getBookByAuthor(bookAuthor);
        if(bookList.size() == 0){
            logger.info("cannot find a book by the author matching this query: " + bookAuthor);
            return "redirect:/books/shelf";
        }

        logger.info("got " + bookList.size() + " books by " + bookAuthor);
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookList);
        return "book_shelf";
    }

    @GetMapping("/findBySize")
    public String getBySize( Model model, @RequestParam(value = "bookSize") Integer bookSize){
        List<Book> bookList = bookService.getBookBySize(bookSize);
        if(bookList.size() == 0){
            logger.info("cannot find books with the specified size: " + bookSize);
            return "redirect:/books/shelf";
        }

        logger.info("got " + bookList.size() + " books with the size of " + bookSize);
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookList);
        return "book_shelf";
    }
}
