package com.nye.myWay.dto;

import com.nye.myWay.dto.cartItemDTOs.BookResponseUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseUserOneBookDTO {

    private String message;
    private BookResponseUserDTO bookResponseUserDTO;

}
