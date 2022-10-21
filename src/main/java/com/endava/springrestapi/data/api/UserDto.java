package com.endava.springrestapi.data.api;

import com.endava.springrestapi.data.entitie.Role;
import lombok.*;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @Pattern(regexp = "([a-zA-Z',.-]+( [a-zA-Z',.-]+)*){2,30}",message = "The first name must contain only alpha characters and 2-30 characters")
    private String firstName;
    @Pattern(regexp = "([a-zA-Z',.-]+( [a-zA-Z',.-]+)*){2,30}",message = "The last name must contain only alpha characters and 2-30 characters")
    private String lastName;
    @Pattern(regexp = "([a-zA-Z',.-]+( [a-zA-Z',.-]+)*){3,30}",message = "The city  must contain alpha only characters and 3-30 characters")
    private String city;
    @Pattern(regexp ="^[[A-Z]|[a-z]][[A-Z]|[a-z]|\\d|[_]]{7,29}$",message = "The login  must contain letters and numbers between 7 and 29 characters")
    private String username;
    @Email(message = "Invalid email address format")
    private String accountEmail;
    private String  password;
    private Collection<Role> roles=new ArrayList<>();


}
