package com.harshit.book_rental_system.dto;

import com.harshit.book_rental_system.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    private String email;

    @NotEmpty
    @Length(min=8)
    private String password;

    @NonNull
    private String role;
}
