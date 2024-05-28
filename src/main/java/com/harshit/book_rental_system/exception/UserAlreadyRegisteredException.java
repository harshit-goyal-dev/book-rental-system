package com.harshit.book_rental_system.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserAlreadyRegisteredException extends RuntimeException{

    public UserAlreadyRegisteredException(String message){
        super(message);
    }
}
