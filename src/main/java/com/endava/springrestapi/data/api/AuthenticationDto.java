package com.endava.springrestapi.data.api;

import lombok.Data;
import javax.validation.constraints.Pattern;

@Data
public class AuthenticationDto {
    @Pattern(regexp = "^[0-9]{10}$")
    private Integer userId;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",message = "Minimum eight characters, at least one letter and one number")
    private String password;
}
