package com.harshit.book_rental_system.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
