package com.harshit.book_rental_system.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BookRentalLimitExceededException extends RuntimeException{

    public BookRentalLimitExceededException(String message){
        super(message);
    }
}
