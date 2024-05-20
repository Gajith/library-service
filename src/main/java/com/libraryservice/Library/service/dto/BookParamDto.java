package com.libraryservice.Library.service.dto;

import com.libraryservice.Library.service.exception.CreateGroup;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookParamDto {

    @NotEmpty(groups = CreateGroup.class)
    private String isbn;
    @NotNull(groups = CreateGroup.class)
    private String title;
    @NotNull(groups = CreateGroup.class)
    private String author;


}
