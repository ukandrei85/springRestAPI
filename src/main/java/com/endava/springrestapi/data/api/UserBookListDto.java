package com.endava.springrestapi.data.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class UserBookListDto {
   @Pattern(regexp = "^[0-9]{10}$")
    private Integer userId;
   @Pattern(regexp = "^[0-9]{10}$")
    private Integer bookId;


}
