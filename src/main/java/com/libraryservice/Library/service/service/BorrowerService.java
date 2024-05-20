package com.libraryservice.Library.service.service;

import com.libraryservice.Library.service.dto.BookDto;
import com.libraryservice.Library.service.dto.BorrowerDto;
import com.libraryservice.Library.service.exception.ResourceNotFoundException;
import com.libraryservice.Library.service.persistence.*;
import com.libraryservice.Library.service.repository.BookInventoryRepository;
import com.libraryservice.Library.service.repository.BookRepository;
import com.libraryservice.Library.service.repository.BorrowerBookRepository;
import com.libraryservice.Library.service.repository.BorrowerRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j
public class BorrowerService {

    private final MessageSource messageSource;


    private final BorrowerRepository borrowerRepository;
    private final BookInventoryRepository bookInventoryRepository;
    private final BorrowerBookRepository borrowerBookRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BorrowerService(MessageSource messageSource, BorrowerRepository borrowerRepository, BookInventoryRepository bookInventoryRepository, BorrowerBookRepository borrowerBookRepository, BookRepository bookRepository) {
        this.messageSource = messageSource;
        this.borrowerRepository = borrowerRepository;
        this.bookInventoryRepository = bookInventoryRepository;
        this.borrowerBookRepository = borrowerBookRepository;
        this.bookRepository = bookRepository;
    }

    public BorrowerDto register(BorrowerDto borrowerDto) {
        Borrower newBorrower = new Borrower(borrowerDto.getName(), borrowerDto.getEmail());
        newBorrower = borrowerRepository.save(newBorrower);
        borrowerDto.setBorrowerId(newBorrower.getBorrowerId());
        return borrowerDto;
    }

    @Transactional
    public String borrow(Integer borrowerId, Integer bookId, Locale locale) throws ResourceNotFoundException {
        Optional<List<BookInventory>> bookInventories = bookInventoryRepository.findByBookIdBookId(bookId);
        if (bookInventories.isPresent() && !bookInventories.get().isEmpty()) {
            Optional<BookInventory> inventory = bookInventories.get().stream().filter(bookInventory -> bookInventory.getAvailable().equals(true)).findFirst();
            if (inventory.isPresent()) {
                BookInventory book = inventory.get();
                book.setAvailable(false);
                bookInventoryRepository.save(book);
                borrowerBookRepository.save(new BorrowerBook(borrowerId, bookId));
            } else {
                log.debug("requested book is not found :BookId"+bookId+"by borrower id:"+borrowerId);
                throw new ResourceNotFoundException(messageSource.getMessage("book.is_not_found",null,locale));
            }

        } else {
            throw new ResourceNotFoundException(messageSource.getMessage("book.is_not_found",null,locale));
        }

        return "Book is successfully borrowed";
    }

    public BookDto getBorrowedBook(Integer borrowerId, Integer bookId,Locale locale) throws ResourceNotFoundException {
        Optional<BorrowerBook> borrowBook = borrowerBookRepository.findById(new BorrowerBookPK(borrowerId, bookId));
        BookDto bookDto = null;
        if (borrowBook.isPresent()) {
            Book borrowedBook = bookRepository.findById(bookId).get();
            bookDto = new BookDto(borrowedBook.getBookId(), borrowedBook.getIsbn(), borrowedBook.getTitle(), borrowedBook.getAuthor());
        } else {
            throw new ResourceNotFoundException(messageSource.getMessage("book.is_not_found",null,locale));
        }

        return bookDto;
    }


}
