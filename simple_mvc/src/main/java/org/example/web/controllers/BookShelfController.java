package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
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
        logger.info(this.toString());
        List<Book> bookList = bookService.getAllBooks();
        model.addAttribute("book", new Book());
        model.addAttribute("bookIdToRemove", new BookIdToRemove());
        model.addAttribute("bookList", bookList);
        model.addAttribute("bookTitle", new BookTitle());
        model.addAttribute("bookAuthor", new BookAuthor());
        model.addAttribute("bookSize", new BookSize());
        logger.info("got book shelf with the size " + bookList.size());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            model.addAttribute("bookList", bookService.getAllBooks());
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
            model.addAttribute("bookTitle", new BookTitle());
            model.addAttribute("bookAuthor", new BookAuthor());
            model.addAttribute("bookSize", new BookSize());
            return "book_shelf";
        } else {
            bookService.saveBook(book);
            logger.info("current repository size: " + bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/remove")
    public String removeBook(@Valid BookIdToRemove bookIdToRemove, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", new Book());
            model.addAttribute("bookList", bookService.getAllBooks());
            model.addAttribute("bookIdToRemove", bookIdToRemove);
            model.addAttribute("bookTitle", new BookTitle());
            model.addAttribute("bookAuthor", new BookAuthor());
            model.addAttribute("bookSize", new BookSize());
            return "book_shelf";
        } else {
            bookService.removeBookById(bookIdToRemove.getId());
            return "redirect:/books/shelf";
        }
    }


    @PostMapping("/removeByTitle")
    public String removeBookByTitle(@Valid BookTitle bookTitle, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", new Book());
            model.addAttribute("bookList", bookService.getAllBooks());
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
            model.addAttribute("bookTitle", bookTitle);
            model.addAttribute("bookAuthor", new BookAuthor());
            model.addAttribute("bookSize", new BookSize());
            logger.info("cannot find and remove a book with the title matching this query: " + bookTitle.getTitle());
            return "book_shelf";
        } else {
            bookService.removeBookByTitle(bookTitle.getTitle());
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/removeByAuthor")
    public String removeBookByAuthor(@Valid BookAuthor bookAuthor, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", new Book());
            model.addAttribute("bookList", bookService.getAllBooks());
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
            model.addAttribute("bookTitle", new BookTitle());
            model.addAttribute("bookAuthor", bookAuthor);
            model.addAttribute("bookSize", new BookSize());
            logger.info("cannot find and remove a book with the author name matching this query: " + bookAuthor.getAuthor());
            return "book_shelf";
        } else {
            bookService.removeBookByAuthor(bookAuthor.getAuthor());
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/removeBySize")
    public String removeBookBySize(@Valid BookSize bookSize, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", new Book());
            model.addAttribute("bookList", bookService.getAllBooks());
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
            model.addAttribute("bookTitle", new BookTitle());
            model.addAttribute("bookAuthor", new BookAuthor());
            model.addAttribute("bookSize", bookSize);
            logger.info("cannot find a book with the specified size: " + bookSize.getSize());
            return "book_shelf";
        } else {
            bookService.removeBookBySize(bookSize.getSize());
            return "redirect:/books/shelf";
        }
    }

    @GetMapping("/findByTitle")
    public String getByTitle(@Valid BookTitle bookTitle, BindingResult bindingResult, Model model) {
        List<Book> bookList = bookService.getBookByTitle(bookTitle.getTitle());
        if (bindingResult.hasErrors()) {
            model.addAttribute("bookList", bookService.getAllBooks());
        } else {
            bookService.getBookByTitle(bookTitle.getTitle());
            logger.info("got " + bookList.size() + " books filtered by the title " + bookTitle.getTitle());
            model.addAttribute("bookList", bookList);
        }
        model.addAttribute("book", new Book());
        model.addAttribute("bookIdToRemove", new BookIdToRemove());
        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("bookAuthor", new BookAuthor());
        model.addAttribute("bookSize", new BookSize());
        return "book_shelf";
    }

    @GetMapping("/findByAuthor")
    public String getByAuthor(@Valid BookAuthor bookAuthor, BindingResult bindingResult, Model model) {
        List<Book> bookList = bookService.getBookByAuthor(bookAuthor.getAuthor());
        if (bindingResult.hasErrors()) {
            model.addAttribute("bookList", bookService.getAllBooks());
        } else {
            bookService.getBookByAuthor(bookAuthor.getAuthor());
            logger.info("got " + bookList.size() + " books filtered by the author name " + bookAuthor.getAuthor());
            model.addAttribute("bookList", bookList);
        }
        model.addAttribute("book", new Book());
        model.addAttribute("bookIdToRemove", new BookIdToRemove());
        model.addAttribute("bookTitle", new BookTitle());
        model.addAttribute("bookAuthor", bookAuthor);
        model.addAttribute("bookSize", new BookSize());
        return "book_shelf";
    }

    @GetMapping("/findBySize")
    public String getBySize(@Valid BookSize bookSize, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("cannot find books with the specified size: " + bookSize.getSize());
            model.addAttribute("bookList", bookService.getAllBooks());
        } else {
            List<Book> bookList = bookService.getBookBySize(bookSize.getSize());
            bookService.getBookBySize(bookSize.getSize());
            logger.info("got " + bookList.size() + " books filtered by the size specified " + bookSize.getSize());
            model.addAttribute("bookList", bookList);
        }
        model.addAttribute("book", new Book());
        model.addAttribute("bookIdToRemove", new BookIdToRemove());
        model.addAttribute("bookTitle", new BookTitle());
        model.addAttribute("bookAuthor", new BookAuthor());
        model.addAttribute("bookSize", bookSize);
        return "book_shelf";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

            String name = file.getOriginalFilename();
            byte[] bytes = new byte[0];

            bytes = file.getBytes();
        if (bytes.length != 0) {

            String rootPath = System.getProperty("catalina.home");
            File dir = new File(rootPath + File.separator + "externalUploads");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            //create file
            File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            logger.info("new file saved at: " + serverFile.getAbsolutePath());

            return "redirect:/books/shelf";
        } else {
            logger.info("Could not upload the file: the file was not specified or doesn't exist");
            throw new IOException("You didn't seem to specify the file to upload");
        }
    }

    @ExceptionHandler(IOException.class)
    public String handleError(Model model, IOException e) {
        model.addAttribute("errorMessage", e.getMessage());
        return "errors/500";
    }
}