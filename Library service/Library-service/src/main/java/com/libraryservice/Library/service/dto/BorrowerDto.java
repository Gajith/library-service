package com.libraryservice.Library.service.dto;

import com.libraryservice.Library.service.exception.CreateGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowerDto {

    private Integer borrowerId;
    @NotNull(groups = CreateGroup.class)
    private String name;
    @NotNull(groups = CreateGroup.class)
    @Email(groups = CreateGroup.class)
    private String email;
}
