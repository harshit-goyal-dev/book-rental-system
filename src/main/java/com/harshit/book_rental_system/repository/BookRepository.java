package com.harshit.book_rental_system.repository;

import com.harshit.book_rental_system.entity.Book;
import com.harshit.book_rental_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
