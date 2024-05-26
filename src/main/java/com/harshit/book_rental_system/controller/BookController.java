package com.harshit.book_rental_system.controller;

import com.harshit.book_rental_system.dto.BookRequestDto;
import com.harshit.book_rental_system.dto.RentRequestDto;
import com.harshit.book_rental_system.dto.UserRequestDto;
import com.harshit.book_rental_system.dto.UserResponseDto;
import com.harshit.book_rental_system.entity.Book;
import com.harshit.book_rental_system.service.IBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BookController.APPLICATION_ENDPOINT)
public class BookController {
    protected static final String APPLICATION_ENDPOINT = "/book-rental/api/v1";
    private static final String BOOK_ENDPOINT = "books";

    @Autowired
    private IBookService bookService;

    @PostMapping(BOOK_ENDPOINT)
    public ResponseEntity<Book> addBook(@RequestBody @Valid BookRequestDto bookRequestDto){

        return ResponseEntity.status(HttpStatusCode.valueOf(201)).
                body(bookService.createBook(bookRequestDto));
    }

    @PostMapping(BOOK_ENDPOINT+"/{bookId}/rent")
    public ResponseEntity<String> rentBook(@PathVariable long bookId, @RequestBody @Valid RentRequestDto rentRequestDto){
        bookService.rentBook(bookId, rentRequestDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("Book rented succesfully");
    }

    @PostMapping(BOOK_ENDPOINT+"/{bookId}/return")
    public ResponseEntity<String> returnBook(@PathVariable long bookId){
        bookService.returnBook(bookId);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("Book returned succesfully");
    }

    @GetMapping(BOOK_ENDPOINT)
    public ResponseEntity<List<Book>> getExams(){
        return ResponseEntity.ok().body(bookService.findAllBooks());
    }

    @GetMapping(BOOK_ENDPOINT+"/{id}")
    public ResponseEntity<Book> getExamById(@PathVariable long id){
        return ResponseEntity.ok().body(bookService.findBookById(id));
    }

    @PutMapping(BOOK_ENDPOINT+"/{id}")
    public ResponseEntity<Book> updateExamById(@PathVariable long id, @RequestBody @Valid BookRequestDto bookRequestDto){
        return ResponseEntity.ok().body(bookService.updateBookById(id,bookRequestDto));

    }
    @DeleteMapping(BOOK_ENDPOINT+"/{id}")
    public ResponseEntity<String> deleteExamById(@PathVariable long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().body("Deleted Successfully");

    }
}
