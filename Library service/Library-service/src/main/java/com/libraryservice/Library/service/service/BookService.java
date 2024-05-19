package com.libraryservice.Library.service.service;

import com.libraryservice.Library.service.dto.BookDto;
import com.libraryservice.Library.service.dto.BookParamDto;
import com.libraryservice.Library.service.exception.DataValidationException;
import com.libraryservice.Library.service.persistence.Book;
import com.libraryservice.Library.service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto addBook(BookParamDto bookDto) throws DataValidationException {
        BookDto newBookDto = null;
        if (bookRepository.findByIsbn(bookDto.getIsbn()).isPresent()) {
            throw new DataValidationException("Book exists for given isbn");
        } else {
            Book newBook = new Book(bookDto.getIsbn(), bookDto.getTitle(), bookDto.getAuthor());
            newBook = bookRepository.save(newBook);
            newBookDto = new BookDto(newBook.getBookId(), bookDto.getIsbn(), bookDto.getTitle(), bookDto.getAuthor());

        }
        return newBookDto;
    }

    public List<BookDto> getAll() {
        return bookRepository.findAll().stream().map(book -> {
            return new BookDto(book.getBookId(), book.getIsbn(), book.getTitle(), book.getAuthor());
        }).collect(Collectors.toList());

    }
}
