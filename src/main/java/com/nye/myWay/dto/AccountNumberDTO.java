package com.nye.myWay.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.AccessType;

@Data
@AllArgsConstructor
public class AccountNumberDTO {

    // TODO: 2024. 06. 01.
    //how to valid?
    private String accountNumber;
}
