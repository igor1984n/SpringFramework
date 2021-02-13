package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
        logger.info("got book shelf");
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getAllBooks());
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
            logger.info("cannot find a book with this title: " + bookTitle);
        }
        return "redirect:/books/shelf";
    }

    @PostMapping("/removeByAuthor")
    public String removeBookByAuthor(@RequestParam(value = "bookAuthor") String bookAuthor){
        if(!bookService.removeBookByAuthor(bookAuthor)){
            logger.info("cannot find a book with this author: " + bookAuthor);
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
        int listSize = bookService.getBookByTitle(bookTitle).size();
        if(listSize == 0){
            logger.info("cannot get a book with this title: " + bookTitle);
            return "redirect:/books/shelf";
        }
        logger.info("got " + listSize + " books filtered by title");
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getBookByTitle(bookTitle));
        return "book_shelf";
    }

    @GetMapping("/findByAuthor")
    public String getByAuthor(@RequestParam(value="bookAuthor") String bookAuthor, Model model){
        int listSize = bookService.getBookByAuthor(bookAuthor).size();
        if(listSize == 0){
            logger.info("cannot find a book by this author: " + bookAuthor);
            return "redirect:/books/shelf";
        }

        logger.info("got " + listSize + " books by " + bookAuthor);
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getBookByAuthor(bookAuthor));
        return "book_shelf";
    }

    @GetMapping("/findBySize")
    public String getBySize( Model model, @RequestParam(value = "bookSize") Integer bookSize){

        int listSize = bookService.getBookBySize(bookSize).size();
        if(listSize == 0){
            logger.info("cannot find books with the specified size: " + bookSize);
            return "redirect:/books/shelf";
        }

        logger.info("got " + listSize + " books with the size of " + bookSize);
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", bookService.getBookBySize(bookSize));
        return "book_shelf";
    }
}
