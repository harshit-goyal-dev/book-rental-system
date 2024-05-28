package com.harshit.book_rental_system.service;

import com.harshit.book_rental_system.dto.JwtAuthenticationResponse;
import com.harshit.book_rental_system.dto.SignInRequest;
import com.harshit.book_rental_system.dto.UserRequestDto;
import com.harshit.book_rental_system.entity.User;

public interface IAuthenticationService {
    User signUp(UserRequestDto userRequestDto);
    JwtAuthenticationResponse signin(SignInRequest signInRequest);
}
