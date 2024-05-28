package com.harshit.book_rental_system.service;

import com.harshit.book_rental_system.dto.BookRequestDto;
import com.harshit.book_rental_system.dto.RentRequestDto;
import com.harshit.book_rental_system.entity.Book;
import com.harshit.book_rental_system.entity.User;
import com.harshit.book_rental_system.exception.*;
import com.harshit.book_rental_system.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{

    @Autowired
    private IUserService userService;
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(BookRequestDto bookRequestDto) {
        Book book = new Book(bookRequestDto);
        return bookRepository.save(book);
    }

    @Override
    public Book findBookById(long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(!optionalBook.isPresent())throw new BookNotFoundException("Book with "+id+" doesn't exist");
        return optionalBook.get();
    }

    @Override
    public Book updateBookById(long id, BookRequestDto bookRequestDto) {
        Book book = findBookById(id);
        book.setAuthor(bookRequestDto.getAuthor());
        book.setTitle((bookRequestDto.getTitle()));
        book.setGenre((bookRequestDto.getGenre()));
        return bookRepository.save(book);
    }
    @Override
    public void deleteBookById(long id) {
        Book book = findBookById(id);
        bookRepository.delete(book);
    }

    @Override
    public void rentBook(long bookId, RentRequestDto rentRequestDto) {
        long userId = rentRequestDto.getUserId();
        Book book = findBookById(bookId);
        if(!book.isAvailable())
            throw new BookNotAvailableException("This book is already rented");
        User user = userService.getUserById(userId);
        if(user.getBooks().size()>=2)
            throw new BookRentalLimitExceededException("User cannot rent more than two books at the same time");
        user.getBooks().add(book);
        book.setAvailable(false);
        book.setUser(user);
        //userService.updateUser(user);
        bookRepository.save(book);
    }

    @Override
    public void returnBook(long bookId) {
        Book book = findBookById(bookId);
        if(book.isAvailable())
            throw new BookNotRentedException("This book is not rented currently");

        User bookUser = book.getUser();
        if(bookUser==null)throw new UserNotFoundException();
        long userId = bookUser.getId();
        User user = userService.getUserById(userId);

        user.getBooks().remove(book);
        book.setAvailable(true);
        book.setUser(null);
        //userService.updateUser(user);
        bookRepository.save(book);
    }

}
