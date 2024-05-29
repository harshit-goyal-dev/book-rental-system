package com.harshit.book_rental_system.exceptionHandler;

import com.harshit.book_rental_system.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.Exception;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotAvailableException.class)
    public ResponseEntity<String> handleBookNotAvailableException(BookNotAvailableException exception){
        return new ResponseEntity<String>(exception.getMessage().toString(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BookNotRentedException.class)
    public ResponseEntity<String> handleBookNotRentedException(BookNotRentedException exception){
        return new ResponseEntity<String>(exception.getMessage().toString(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BookRentalLimitExceededException.class)
    public ResponseEntity<String> handleBookRentalLimitExceededException(BookRentalLimitExceededException exception){
        return new ResponseEntity<String>(exception.getMessage().toString(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<String> handleInvalidRoleException(InvalidRoleException exception){
        return new ResponseEntity<String>(exception.getMessage().toString(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
