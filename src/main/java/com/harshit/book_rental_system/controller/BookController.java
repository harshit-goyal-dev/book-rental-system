package com.harshit.book_rental_system.controller;

import com.harshit.book_rental_system.dto.BookRequestDto;
import com.harshit.book_rental_system.dto.RentRequestDto;
import com.harshit.book_rental_system.dto.UserRequestDto;
import com.harshit.book_rental_system.dto.UserResponseDto;
import com.harshit.book_rental_system.entity.Book;
import com.harshit.book_rental_system.enums.Role;
import com.harshit.book_rental_system.service.IBookService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BookController.APPLICATION_ENDPOINT)
public class BookController {
    protected static final String APPLICATION_ENDPOINT = "/book-rental/api/v1";
    private static final String BOOK_ENDPOINT = "books";

    @Autowired
    private IBookService bookService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(BOOK_ENDPOINT)
    public ResponseEntity<Book> addBook(@RequestBody @Valid BookRequestDto bookRequestDto){

        return ResponseEntity.status(HttpStatusCode.valueOf(201)).
                body(bookService.createBook(bookRequestDto));
    }
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(BOOK_ENDPOINT+"/{bookId}/rent")
    public ResponseEntity<String> rentBook(@PathVariable long bookId, @RequestBody @Valid RentRequestDto rentRequestDto){
        bookService.rentBook(bookId, rentRequestDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("Book rented succesfully");
    }
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(BOOK_ENDPOINT+"/{bookId}/return")
    public ResponseEntity<String> returnBook(@PathVariable long bookId){
        bookService.returnBook(bookId);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("Book returned succesfully");
    }
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(BOOK_ENDPOINT)
    public ResponseEntity<List<Book>> getBooks(){
        return ResponseEntity.ok().body(bookService.findAllBooks());
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(BOOK_ENDPOINT+"/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id){
        return ResponseEntity.ok().body(bookService.findBookById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(BOOK_ENDPOINT+"/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable long id, @RequestBody @Valid BookRequestDto bookRequestDto){
        return ResponseEntity.ok().body(bookService.updateBookById(id,bookRequestDto));

    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(BOOK_ENDPOINT+"/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().body("Deleted Successfully");

    }
}
