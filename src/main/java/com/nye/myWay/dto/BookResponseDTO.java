package com.nye.myWay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookResponseDTO {

    private String message;
    private BookResponseAdminDTO bookResponseAdminDTO;
}
