package com.ayodele.readingisgood.api;

import com.ayodele.readingisgood.error.ApiErrorResponse;
import com.ayodele.readingisgood.error.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(
            BookNotFoundException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse("error-0001",
                        "No book found with ID " + ex.getId());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
