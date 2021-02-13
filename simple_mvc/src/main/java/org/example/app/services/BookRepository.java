package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookRepository implements ProjectRepository<Book> {


    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retrieveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        if (book.getTitle().equals("") && book.getAuthor().equals("") && book.getSize() == null) {
            logger.info("unable to save an empty entry");
        } else {
            book.setId(book.hashCode());
            if(book.getSize() == null){
                book.setSize(0);
            }
            logger.info("store new book: " + book);
            repo.add(book);
        }
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        if (!bookExists(bookIdToRemove))
            return false;
        else {
            for (Book book : retrieveAll()) {
                if (book.getId().equals(bookIdToRemove)) {
                    logger.info("removed book: " + book);
                    repo.remove(book);
                }
            }
        }
        return true;
    }

    public boolean removeItemByTitle(String bookTitleToRemove) {
        if (!bookExists(bookTitleToRemove)) {
            return false;
        } else {
            for (Book book : retrieveAll()) {
                if (book.getTitle().equals(bookTitleToRemove)) {
                    logger.info("removed book: " + book);
                    repo.remove(book);
                }
            }
        }
        return true;
    }

    @Override
    public boolean removeItemsByAuthor(String bookAuthorToRemove) {
        if(!booksExist(bookAuthorToRemove)){
            return false;
        } else{
            for(Book book :retrieveAll()){
                if(book.getAuthor().equals(bookAuthorToRemove)) {
                    logger.info("removed book: " + book);
                    repo.remove(book);
                }
            }
        }
        return true;
    }

    @Override
    public boolean removeItemsBySize(Integer bookSizeToRemove) {
        if(!canFindSize(bookSizeToRemove)){
        return false;
        }
        else{
            for(Book book : retrieveAll()){
                if(book.getSize().equals(bookSizeToRemove)){
                    logger.info("removed book " + book);
                    repo.remove(book);
                }
            }
        }
        return true;
    }

    @Override
    public List<Book> getItemByTitle(String bookTitle) {
        if(!bookExists(bookTitle)){
            return new ArrayList<>(0);
        }else{
            List<Book>booksToRetrieve = new ArrayList<>();
            for(Book book : retrieveAll()){
                if(book.getTitle().equals(bookTitle)){
                    booksToRetrieve.add(book);
                    logger.info("found book " + book);
                }
            }
            return booksToRetrieve;
        }
    }

    @Override
    public List<Book> getItemByAuthor(String bookAuthor) {
       if(!booksExist(bookAuthor)){
           return new ArrayList<>(0);
       }
       else{
           List<Book>books2retrieve = new ArrayList<>();
           for(Book book : retrieveAll()){
               if(book.getAuthor().equals(bookAuthor)){
                   books2retrieve.add(book);
                   logger.info("found book " + book);
               }
           }return books2retrieve;
       }
    }

    @Override
    public List<Book> getItemBySize(Integer bookSize) {
        if(!canFindSize(bookSize)){
            return new ArrayList<>(0);
        } else{
            List<Book> books2retrieve = new ArrayList<>();
            for(Book book : retrieveAll()){
                if(book.getSize().equals(bookSize)){
                    books2retrieve.add(book);
                    logger.info("found book " + book);
                }
            }return books2retrieve;
        }
    }

    private boolean canFindSize(Integer bookSize){
        List<Integer> sizes = retrieveAll().stream()
                .map(Book:: getSize)
                .collect(Collectors.toList());
        return sizes.contains(bookSize);
    }

    private boolean bookExists(Integer bookId) {
        List<Integer> ids = retrieveAll().stream()
                .map(Book::getId)
                .collect(Collectors.toList());
        return ids.contains(bookId);
    }

    private boolean bookExists(String bookTitle) {
        List<String> titles = retrieveAll().stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
        return titles.contains(bookTitle);
    }

    private boolean booksExist(String author){
        List<String> authors = retrieveAll().stream()
                .map(Book::getAuthor)
                .collect(Collectors.toList());
        return authors.contains(author);
    }



}
