package com.nye.myWay.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Valid
public class RegisterDTO {
    String name;
    String username;
    @Email
    String email;
    String password;
}
