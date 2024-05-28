package com.harshit.book_rental_system.service;

import com.harshit.book_rental_system.dto.RentRequestDto;
import com.harshit.book_rental_system.entity.Book;
import com.harshit.book_rental_system.entity.User;
import com.harshit.book_rental_system.enums.Role;
import com.harshit.book_rental_system.exception.BookNotAvailableException;
import com.harshit.book_rental_system.exception.BookNotFoundException;
import com.harshit.book_rental_system.exception.BookNotRentedException;
import com.harshit.book_rental_system.exception.BookRentalLimitExceededException;
import com.harshit.book_rental_system.repository.BookRepository;
import com.harshit.book_rental_system.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {


    @Mock
    private BookRepository bookRepository;
    @Mock
    private UserService userService;
    @InjectMocks
    private BookService bookService;

    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;

    private User user1;

    private User user2;
    @BeforeEach
    void setUp() {
        book1 = new Book(1L, "Title1", "Author1", "Genre1");
        book2 = new Book(2L, "Title2", "Author2", "Genre2");
        book3 = new Book(3L, "Title3", "Author3", "Genre3");
        book4 = new Book(4L, "Title4", "Author4", "Genre4");

        user1 = new User(1L,"abcd","xyz","abcd@gmail.com",
                "qwertyui", Role.USER,new ArrayList<Book>());
        user2 = new User(2L,"zxc","xyz","pqr@gmail.com",
                "qwertyui", Role.USER,new ArrayList<Book>());
        user1.getBooks().add(book1);
        book1.setAvailable(false);
        user1.getBooks().add(book2);
        book2.setAvailable(false);
    }

    @Test
    public void rentBookTest_ShouldThrowBookNotFoundException() throws BookNotFoundException{
        when(bookRepository.findById(5L)).thenThrow(BookNotFoundException.class);
        Assertions.assertThrows(BookNotFoundException.class, () -> bookService
                .rentBook(5L,new RentRequestDto(ArgumentMatchers.anyLong())));
    }

    @Test
    public void rentBookTest_ShouldThrowBookNotAvailableException(){
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));

        Assertions.assertThrows(BookNotAvailableException.class, () -> bookService
                .rentBook(1L,new RentRequestDto(ArgumentMatchers.anyLong())));
    }

    @Test
    public void rentBookTest_ShouldThrowBookRentalLimitExceededException(){
        when(bookRepository.findById(3L)).thenReturn(Optional.of(book3));
        when(userService.getUserById(1L)).thenReturn(user1);

        Assertions.assertThrows(BookRentalLimitExceededException.class, () -> bookService
                .rentBook(3L,new RentRequestDto(1L)));
    }

    @Test
    public void rentBookTestShouldRentBook(){
        when(bookRepository.findById(3L)).thenReturn(Optional.of(book3));
        when(userService.getUserById(2L)).thenReturn(user2);
        bookService.rentBook(3L,new RentRequestDto(2L));
        assertFalse(book3.isAvailable());
        Assertions.assertEquals(1,user2.getBooks().size());
    }

    @Test
    public void returnBookTest_ShouldThrowBookNotRentedException(){
        when(bookRepository.findById(3L)).thenReturn(Optional.of(book3));
        Assertions.assertThrows(BookNotRentedException.class, () -> bookService.returnBook(3L));
        assertTrue(book3.isAvailable());
        Assertions.assertEquals(0,user2.getBooks().size());
    }

    @Test
    public void returnBookTest_ShouldReturnBook(){
        when(bookRepository.findById(4L)).thenReturn(Optional.of(book4));
        when(userService.getUserById(2L)).thenReturn(user2);
        //bookService.rentBook(3L,new RentRequestDto(2L));
        user2.getBooks().add(book4);
        book4.setUser(user2);
        book4.setAvailable(false);
        bookService.returnBook(4L);
        assertTrue(book3.isAvailable());
        Assertions.assertEquals(0,user2.getBooks().size());
    }
}
