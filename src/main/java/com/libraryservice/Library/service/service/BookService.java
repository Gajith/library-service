package com.libraryservice.Library.service.service;

import com.libraryservice.Library.service.dto.BookDto;
import com.libraryservice.Library.service.dto.BookParamDto;
import com.libraryservice.Library.service.exception.DataValidationException;
import com.libraryservice.Library.service.persistence.Book;
import com.libraryservice.Library.service.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookService {

    private final MessageSource messageSource;
    private final BookRepository bookRepository;

    @Autowired
    public BookService(MessageSource messageSource, BookRepository bookRepository) {
        this.messageSource = messageSource;
        this.bookRepository = bookRepository;
    }

    public BookDto addBook(BookParamDto bookDto, Locale locale) throws DataValidationException {
        BookDto newBookDto = null;
        if (bookRepository.findByIsbn(bookDto.getIsbn()).isPresent()) {
            log.debug("book already exists for given isbn "+ bookDto.getIsbn());
            throw new DataValidationException(messageSource.getMessage("book.exists_for_given_isbn",null,locale));
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
