package com.harshit.book_rental_system.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BookNotAvailableException extends RuntimeException{

    public BookNotAvailableException(String message){
        super(message);
    }
}
