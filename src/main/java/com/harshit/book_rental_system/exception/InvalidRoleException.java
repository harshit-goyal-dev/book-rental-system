package com.harshit.book_rental_system.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidRoleException extends RuntimeException{

    public InvalidRoleException(String message){
        super(message);
    }
}
