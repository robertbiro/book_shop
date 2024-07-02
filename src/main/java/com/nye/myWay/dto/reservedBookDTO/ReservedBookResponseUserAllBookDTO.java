package com.nye.myWay.dto.reservedBookDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// generic class
public class ReservedBookResponseUserAllBookDTO<T> {

    private String message;
    private T data;

}
