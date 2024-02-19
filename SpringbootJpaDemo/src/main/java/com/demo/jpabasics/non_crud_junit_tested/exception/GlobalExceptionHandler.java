package com.demo.jpabasics.non_crud_junit_tested.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseException> NoSuchElementExceptionHandler(RuntimeException exception) {
        log.info("-----------------GlobalExceptionHandler started execution-------------------");
        // Log the exception for analysis
//        noSuchElementException.printStackTrace();

        // Create an ErrorResponse with appropriate details
        ErrorResponseException errorResponse = new ErrorResponseException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getCause()
        );

        log.info("Error response: " + errorResponse);

        log.info("-----------------GlobalExceptionHandler executed-------------------");
        // Return the ErrorResponse as a ResponseEntity with the specified status code
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
