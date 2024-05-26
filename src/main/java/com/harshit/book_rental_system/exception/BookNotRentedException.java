package com.harshit.book_rental_system.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BookNotRentedException extends RuntimeException{

    public BookNotRentedException(String message){
        super(message);
    }
}
