package com.harshit.book_rental_system.controller;

import com.harshit.book_rental_system.dto.JwtAuthenticationResponse;
import com.harshit.book_rental_system.dto.SignInRequest;
import com.harshit.book_rental_system.service.IAuthenticationService;
import com.harshit.book_rental_system.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookController.APPLICATION_ENDPOINT)
public class AuthenticationController {
    protected static final String APPLICATION_ENDPOINT = "/book-rental/api/v1";
    private static final String SIGNIN_ENDPOINT = "login";

    @Autowired
    private IAuthenticationService authenticationService;


    @PostMapping(SIGNIN_ENDPOINT)
    public ResponseEntity<JwtAuthenticationResponse> addExam(@RequestBody @Valid SignInRequest signInRequest){
        return ResponseEntity.ok().body(authenticationService.signin(signInRequest));
    }
}
