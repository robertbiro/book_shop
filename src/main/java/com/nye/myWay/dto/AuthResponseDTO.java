package com.nye.myWay.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class AuthResponseDTO {
    private String message;
    private String token;
    @JsonIgnore
    private String username;

    public String welcome() {
        return  "Hello" + username;
    }
}
