package com.endava.springrestapi.data.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;


@Data
@AllArgsConstructor
public class BookDto {
    @Pattern(regexp ="^[[A-Z]|[a-z]][[A-Z]|[a-z]|\\d|[_]]{3,29}$",message = "The title must contain letters and numbers between 3 and 30 characters")
    private String title;
    @Pattern(regexp = "^[A-Za-z]+((\\s)?([A-Za-z])+)*$",message = "Mandatory single name, optional additional names, WITH spaces, WITHOUT special characters")
    private String author;
    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$",message = "Should have ISBN format")
    private Long isbn;
    @Pattern(regexp = "^[0-9]{10}$")
    private String userId;
    private Boolean isRented;
    private Boolean isReserved;
}
