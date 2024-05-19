package com.libraryservice.Library.service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libraryservice.Library.service.LibraryServiceApplication;
import com.libraryservice.Library.service.dto.BookDto;
import com.libraryservice.Library.service.dto.BookParamDto;
import com.libraryservice.Library.service.exception.ResourceNotFoundException;
import com.libraryservice.Library.service.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = LibraryServiceApplication.class)
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    @Autowired
    private ObjectMapper objectMapper;

    private BookParamDto bookOne;


    @BeforeEach
    void  setUp(){
        bookOne = new BookParamDto("102-344","Java Handbook","author1");

    }

    @Test
    public void is_addABook() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookOne)))
                .andExpect(status().isCreated());

    }
}
