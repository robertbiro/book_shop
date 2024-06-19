package com.nye.myWay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO {

    private String message;
    private CartReservedBookDTO cartReservedBookDTO;

}
