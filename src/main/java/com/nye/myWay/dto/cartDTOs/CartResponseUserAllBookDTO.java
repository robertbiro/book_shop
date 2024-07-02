package com.nye.myWay.dto.cartDTOs;

import com.nye.myWay.dto.cartItemDTOs.BookResponseUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseUserAllBookDTO {

    private String message;
    private List<BookResponseUserDTO> allBookResponseUserDTO;

}
