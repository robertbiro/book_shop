package com.nye.myWay.dto.cartItemDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DecreaseCartItemDTO<T> {

    private boolean delete;
    private T result;
}
