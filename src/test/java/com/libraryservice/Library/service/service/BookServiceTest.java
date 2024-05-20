package com.libraryservice.Library.service.service;

import com.libraryservice.Library.service.dto.BookDto;
import com.libraryservice.Library.service.dto.BookParamDto;
import com.libraryservice.Library.service.exception.DataValidationException;
import com.libraryservice.Library.service.exception.ResourceNotFoundException;
import com.libraryservice.Library.service.persistence.Book;
import com.libraryservice.Library.service.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {


    @Autowired
    private BookService service;

    private BookDto bookDto1;
    private BookDto bookDto2;


    @Test
    public void is_Book_New_Added(){
        BookParamDto newBook = new BookParamDto("102-1331","TestTitle","TestAuthor");
        BookDto newBk =service.addBook(newBook, Locale.US);
        assert(newBk.getBookId()>0);
    }

    @Test
    public void is_Book_Added_Same_ISBN(){
        BookParamDto newBook = new BookParamDto("102-133","TestTitle1","TestAuthor2");
        //BookDto newBk =
        assertThrows(DataValidationException.class,()->service.addBook(newBook,Locale.US));
    }

    @Test
    public void getAllBooks_ReturnAllBooks(){
      assertThat(service.getAll()).isNotNull();

    }





}
