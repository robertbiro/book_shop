package com.nye.myWay.dto.cartItemDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// generic class
public class CartItemChangeDTO<T> {

    private String message;
    private T data;
}
