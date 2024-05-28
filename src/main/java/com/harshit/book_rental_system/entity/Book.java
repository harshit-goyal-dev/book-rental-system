package com.harshit.book_rental_system.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.harshit.book_rental_system.dto.BookRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String author;

    @NotEmpty
    private String genre;

    private boolean isAvailable;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private User user;

    public Book(BookRequestDto dto){
        this.title = dto.getTitle();
        this.author = dto.getAuthor();
        this.genre = dto.getGenre();
        this.isAvailable = true;
    }

    public Book(long id, String title, String author, String genre){
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = true;
    }
}
