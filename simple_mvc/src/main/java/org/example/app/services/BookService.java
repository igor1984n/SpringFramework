package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
public class BookService {

    private final ProjectRepository<Book> bookRepo;
    private final Logger logger = Logger.getLogger(BookService.class);

    @Autowired
    public BookService(ProjectRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retrieveAll();
    }

    public void defaultInit() {
        logger.info("default INIT book service bean");
    }

    public void defaultDestroy() {
        logger.info("default DESTROY in book service bean");
    }

    public void saveBook(Book book) {
        if (book.getTitle().equals("") && book.getAuthor().equals("") && book.getSize() == null) {
            logger.info("unable to save an empty entry");
        } else {
            bookRepo.store(book);
        }
    }

    public boolean removeBookById(Integer bookIdToRemove) {
        boolean bookExists = getAllBooks().stream()
                .anyMatch(book -> book.getId().equals(bookIdToRemove));
        logger.info("book with id exists: " + bookExists);
        if (bookExists) {
            bookRepo.retrieveAll().stream()
                    .filter(book -> book.getId().equals(bookIdToRemove))
                    .forEach(bookRepo::removeBook);
            return true;
        }
        return false;
    }


    public boolean removeBookByTitle(String bookTitleToRemove) {
        Pattern pattern;
        if (bookTitleToRemove.isEmpty()) {
            pattern = Pattern.compile("");
        } else {
            pattern = Pattern.compile(".*" + bookTitleToRemove + ".*");
        }

        boolean bookExists = bookRepo.retrieveAll()
                .stream()
                .anyMatch(book -> book.getTitle().matches(pattern.pattern()));
        if (bookExists) {
            bookRepo.retrieveAll().stream()
                    .filter(book -> book.getTitle().matches(pattern.pattern()))
                    .peek(book -> logger.info("found and removed a book with the title matching the pattern "
                            + pattern.pattern() + " " + book))
                    .forEach(bookRepo::removeBook);
            return true;
        }
        return false;
    }


    public boolean removeBookByAuthor(String bookAuthorToRemove) {

        Pattern pattern;

        if (bookAuthorToRemove.isEmpty()) {
            pattern = Pattern.compile("");
        } else {
            pattern = Pattern.compile(".*" + bookAuthorToRemove + ".*");
        }

        boolean bookExists = bookRepo.retrieveAll().stream()
                .anyMatch(book -> book.getAuthor().matches(pattern.pattern()));
        if (bookExists) {
            bookRepo.retrieveAll().stream()
                    .filter(book -> book.getAuthor().matches(pattern.pattern()))
                    .peek(book -> logger.info("found and removed a book by the author matching the pattern"
                            + pattern.pattern() + " " + book))
                    .forEach(bookRepo::removeBook);
            return true;
        }
        return false;
    }


    public boolean removeBookBySize(Integer bookSizeToRemove) {
        boolean bookExists = bookRepo.retrieveAll().stream()
                .anyMatch(book -> book.getSize().equals(bookSizeToRemove));
        if (bookExists) {
            bookRepo.retrieveAll().stream()
                    .filter(book -> book.getSize().equals(bookSizeToRemove))
                    .forEach(bookRepo::removeBook);
            return true;
        }
        return false;
    }


    public List<Book> getBookByTitle(String bookTitle) {
        Pattern pattern;
        if (bookTitle.isEmpty()) {
            pattern = Pattern.compile("");
        } else {
            pattern = Pattern.compile(".*" + bookTitle + ".*");
        }
        boolean bookExists = bookRepo.retrieveAll().stream()
                .anyMatch(book -> book.getTitle().matches(pattern.pattern()));
        if (bookExists) {
            return bookRepo.retrieveAll().stream()
                    .filter(book -> book.getTitle().matches(pattern.pattern()))
                    .peek(book -> logger.info("found a book with the title matching the pattern"
                            + pattern.pattern() + " " + book))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>(0);
    }


    public List<Book> getBookByAuthor(String bookAuthor) {
        Pattern pattern;

        if (bookAuthor.isEmpty()) {
            pattern = Pattern.compile("");
        } else {
            pattern = Pattern.compile(".*" + bookAuthor + ".*");
        }

        boolean bookExists = bookRepo.retrieveAll().stream()
                .anyMatch(book -> book.getAuthor().matches(pattern.pattern()));
        if (bookExists) {
            return bookRepo.retrieveAll().stream()
                    .filter(book -> book.getAuthor().matches(pattern.pattern()))
                    .peek(book -> logger.info("found a book by the author matching the pattern "
                            + pattern.pattern() + " " + book))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>(0);
    }

    public List<Book> getBookBySize(Integer bookSize) {
        boolean bookExists = bookRepo.retrieveAll().stream()
                .anyMatch(book -> book.getSize().equals(bookSize));
        if (bookExists) {
            return bookRepo.retrieveAll().stream()
                    .filter(book -> book.getSize().equals(bookSize))
                    .peek(book -> logger.info("found " + book))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>(0);
    }
}
