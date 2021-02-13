package org.example.app.services;

import java.util.List;

public interface ProjectRepository<T> {
    List<T> retrieveAll();

    void store(T book);

    boolean removeItemById(Integer bookIdToRemove);

    boolean removeItemByTitle(String bookTitleToRemove);

    boolean removeItemsByAuthor(String bookAuthorToRemove);

    boolean removeItemsBySize(Integer bookSizeToRemove);

    List<T> getItemByTitle(String bookTitle);

    List<T> getItemByAuthor(String bookAuthor);

    List<T> getItemBySize(Integer bookSize);
}
