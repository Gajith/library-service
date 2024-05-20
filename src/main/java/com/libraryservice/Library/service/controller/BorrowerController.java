package com.libraryservice.Library.service.controller;

import com.libraryservice.Library.service.dto.BookDto;
import com.libraryservice.Library.service.dto.BorrowerDto;
import com.libraryservice.Library.service.exception.CreateGroup;
import com.libraryservice.Library.service.exception.DataValidationException;
import com.libraryservice.Library.service.exception.ResourceNotFoundException;
import com.libraryservice.Library.service.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/v1/borrower")
public class BorrowerController {

    private final BorrowerService borrowerService;

    @Autowired
    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @PostMapping
    public ResponseEntity<BorrowerDto> register(@RequestBody @Validated(CreateGroup.class) BorrowerDto borrowerDto, BindingResult result) throws DataValidationException{
        if(result.hasErrors()){
            throw new DataValidationException(result);
        }
        return new ResponseEntity<>(borrowerService.register(borrowerDto), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/book/{book_id}")
    public ResponseEntity<String> borrow(@PathVariable("id") Integer id, @PathVariable("book_id") Integer bookId, Locale locale) throws ResourceNotFoundException {
        return ResponseEntity.ok(borrowerService.borrow(id, bookId,locale));
    }

    @GetMapping("/{id}/book/{book_id}")
    public ResponseEntity<BookDto> getBorrowedBook(@PathVariable("id") Integer id, @PathVariable("book_id") Integer bookId,Locale locale) throws ResourceNotFoundException {
        return ResponseEntity.ok(borrowerService.getBorrowedBook(id, bookId,locale));
    }


}
