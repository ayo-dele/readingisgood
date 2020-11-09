package com.ayodele.readingisgood.api;

import com.ayodele.readingisgood.entities.Books;
import com.ayodele.readingisgood.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/books")
public class BooksRestController {

    private final BooksService booksService;

    @Autowired
    private BooksRestController(BooksService booksService) {
        this.booksService = booksService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Books> addBooks(@RequestBody @Valid Books books, BindingResult bindingResult) {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || (books == null)) {
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        this.booksService.saveBooks(books);
        return new ResponseEntity<>(books, headers, HttpStatus.CREATED);
    }

}
