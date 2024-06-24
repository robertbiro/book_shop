package com.nye.myWay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseUserDTO {

    private String message;
    private BookResponseUserDTO bookResponseUserDTO;

}
