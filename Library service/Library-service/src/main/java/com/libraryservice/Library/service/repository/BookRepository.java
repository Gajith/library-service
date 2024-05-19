package com.libraryservice.Library.service.repository;

import com.libraryservice.Library.service.persistence.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByIsbn(String isbn);
}
