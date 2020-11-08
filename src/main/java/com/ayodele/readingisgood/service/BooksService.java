package com.ayodele.readingisgood.service;

import com.ayodele.readingisgood.entities.Books;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

public interface BooksService {
    void saveBooks(Books books) throws DataAccessException;

    Optional<Books> findById(Integer bookId);

    Books updateBook(Books book);
}
