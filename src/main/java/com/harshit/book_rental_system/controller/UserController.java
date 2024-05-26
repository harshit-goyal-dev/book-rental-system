package com.harshit.book_rental_system.controller;

import com.harshit.book_rental_system.dto.UserRequestDto;
import com.harshit.book_rental_system.dto.UserResponseDto;
import com.harshit.book_rental_system.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserController.APPLICATION_ENDPOINT)
public class UserController {
    protected static final String APPLICATION_ENDPOINT = "/book-rental/api/v1";
    private static final String USER_ENDPOINT = "users";

    @Autowired
    private IUserService userService;


    @PostMapping(USER_ENDPOINT)
    public ResponseEntity<UserResponseDto> addExam(@RequestBody @Valid UserRequestDto userRequestDto){
        return ResponseEntity.ok().body(userService.createUser(userRequestDto));
    }

    @GetMapping(USER_ENDPOINT)
    public ResponseEntity<List<UserResponseDto>> getExams(){
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

    @GetMapping(USER_ENDPOINT+"/{id}")
    public ResponseEntity<UserResponseDto> getExamById(@PathVariable long id){
        return ResponseEntity.ok().body(userService.findUserById(id));
    }

    @PutMapping(USER_ENDPOINT+"/{id}")
    public ResponseEntity<UserResponseDto> updateExamById(@PathVariable long id, @RequestBody @Valid UserRequestDto userRequestDto){
        return ResponseEntity.ok().body(userService.updateUserById(id,userRequestDto));

    }
    @DeleteMapping(USER_ENDPOINT+"/{id}")
    public ResponseEntity<String> deleteExamById(@PathVariable long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().body("Deleted Successfully");

    }


}
