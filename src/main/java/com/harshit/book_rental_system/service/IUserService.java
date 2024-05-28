package com.harshit.book_rental_system.service;

import com.harshit.book_rental_system.dto.BookRequestDto;
import com.harshit.book_rental_system.dto.UserRequestDto;
import com.harshit.book_rental_system.dto.UserResponseDto;
import com.harshit.book_rental_system.entity.Book;
import com.harshit.book_rental_system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService {

    UserDetailsService userDetailsService();
    public List<UserResponseDto> findAllUsers();

    public UserResponseDto createUser(UserRequestDto userRequestDto);

    public UserResponseDto findUserById(long id);

    public  UserResponseDto updateUserById(long id, UserRequestDto userRequestDto);

    public  User updateUser(User user);

    public void deleteUserById(long id);

    public User getUserById(long id);


}
