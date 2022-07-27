package com.endava.springrestapi.data.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BookToReturnDto {
    @Pattern(regexp = "^[0-9]{10}$")
    private Integer userId;
    @Pattern(regexp ="^[[A-Z]|[a-z]][[A-Z]|[a-z]|\\d|[_]]{3,29}$",message = "The title must contain letters and numbers between 3 and 30 characters")
    private String title;
    @Pattern(regexp = "^[A-Za-z]+((\\s)?([A-Za-z])+)*$",message = "Mandatory single name, optional additional names, WITH spaces, WITHOUT special characters")
    private String author;
    private Boolean isRented;
    private LocalDate endRentPeriod;
    @Pattern(regexp = "([a-zA-Z',.-]+( [a-zA-Z',.-]+)*){2,30}",message = "The first name must contain only alpha characters and 2-30 characters")
    private String firstName;
    @Pattern(regexp = "([a-zA-Z',.-]+( [a-zA-Z',.-]+)*){2,30}",message = "The last name must contain only alpha characters and 2-30 characters")
    private String lastName;
}
