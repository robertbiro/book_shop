package com.nye.myWay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class AuthResponseDTO {
    private String message;
    private String token;
    private String username;
}
