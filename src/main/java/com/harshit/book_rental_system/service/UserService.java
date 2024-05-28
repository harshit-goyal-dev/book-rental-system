package com.harshit.book_rental_system.service;

import com.harshit.book_rental_system.dto.UserRequestDto;
import com.harshit.book_rental_system.dto.UserResponseDto;
import com.harshit.book_rental_system.entity.User;
import com.harshit.book_rental_system.exception.UserAlreadyRegisteredException;
import com.harshit.book_rental_system.exception.UserNotFoundException;
import com.harshit.book_rental_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UserNotFoundException("User with this email not found"));
            }
        };
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
//        List<UserResponseDto> users = userRepository.findAll().stream().map(user->
//                 new UserResponseDto(user.getId(),user.getFirstName()
//                   ,user.getLastName(),user.getEmail(),user.getRole())
//        ).collect(Collectors.toList());
        List<UserResponseDto> users = userRepository.findAll().stream().map(
                                        user-> new UserResponseDto(user)).collect(Collectors.toList());
        return users;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        String email = userRequestDto.getEmail();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent())
            throw new UserAlreadyRegisteredException("User with this email is already registered");
        User user = new User();

        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        user.setRole(user.findRole(userRequestDto.getRole()));
        user.setPassword(new BCryptPasswordEncoder().encode(userRequestDto.getPassword()));
        User createdUser = userRepository.save(user);
        return new UserResponseDto(createdUser);
    }

    @Override
    public UserResponseDto findUserById(long id) {
        User user = getUserById(id);

        return new UserResponseDto(user);
    }

    @Override
    public UserResponseDto updateUserById(long id, UserRequestDto dto) {
        User user = getUserById(id);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
       // user.setEmail(dto.getEmail());
       // user.setPassword(dto.getPassword());
       // user.setRole(user.findRole(dto.getRole()));

        User updatedUser = userRepository.save(user);
        return new UserResponseDto(updatedUser);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    public User getUserById(long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent())throw new UserNotFoundException();

        return optionalUser.get();
    }
}
