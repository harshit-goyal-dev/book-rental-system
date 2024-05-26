package com.harshit.book_rental_system.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {
    @NotEmpty
    private String title;

    @NotEmpty
    private String author;

    @NotEmpty
    private String genre;
}
