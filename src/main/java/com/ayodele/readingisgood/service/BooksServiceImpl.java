package com.ayodele.readingisgood.service;

import com.ayodele.readingisgood.entities.Books;
import com.ayodele.readingisgood.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public void saveBooks(Books books) throws DataAccessException {
        booksRepository.save(books);
    }

    @Override
    public Optional<Books> findById(Integer bookId) {
        return booksRepository.findById(bookId);
    }

    @Override
    public Books updateBook(Books book) {
        return booksRepository.save(book);
    }
}
