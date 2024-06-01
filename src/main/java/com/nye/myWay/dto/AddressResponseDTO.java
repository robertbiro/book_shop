package com.nye.myWay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressResponseDTO {

    private String message;
    private AddressDTO address;

}
