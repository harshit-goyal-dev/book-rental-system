package com.harshit.book_rental_system.service;

import com.harshit.book_rental_system.dto.JwtAuthenticationResponse;
import com.harshit.book_rental_system.dto.SignInRequest;
import com.harshit.book_rental_system.dto.UserRequestDto;
import com.harshit.book_rental_system.entity.User;
import com.harshit.book_rental_system.enums.Role;
import com.harshit.book_rental_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationService implements IAuthenticationService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public User signUp(UserRequestDto userRequestDto){

        User user = new User();

        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        user.setRole(user.findRole(userRequestDto.getRole()));
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signin(SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
                signInRequest.getPassword()));

        User user = userRepository.findByEmail(signInRequest.getEmail().toString()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }
}
