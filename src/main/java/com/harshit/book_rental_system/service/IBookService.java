package com.harshit.book_rental_system.service;

import com.harshit.book_rental_system.dto.BookRequestDto;
import com.harshit.book_rental_system.dto.RentRequestDto;
import com.harshit.book_rental_system.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IBookService {

    public List<Book> findAllBooks();

    public Book createBook(BookRequestDto bookRequestDto);

    public Book findBookById(long id);

    public  Book updateBookById(long id, BookRequestDto bookRequestDto);

    public void deleteBookById(long id);

    public void rentBook(long bookId, RentRequestDto rentRequestDto);

    public void returnBook(long bookId);
}
