package com.libraryservice.Library.service.controller;

import com.libraryservice.Library.service.dto.BookDto;
import com.libraryservice.Library.service.dto.BookParamDto;
import com.libraryservice.Library.service.exception.CreateGroup;
import com.libraryservice.Library.service.exception.DataValidationException;
import com.libraryservice.Library.service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public ResponseEntity<BookDto> addNewBook(@RequestBody @Validated(CreateGroup.class) BookParamDto bookDto, BindingResult result, Locale locale) throws DataValidationException {
        if(result.hasErrors()){
            throw new DataValidationException(result);
        }
        return new ResponseEntity<>(bookService.addBook(bookDto,locale), HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAll() {
        return ResponseEntity.ok(bookService.getAll());
    }


}
