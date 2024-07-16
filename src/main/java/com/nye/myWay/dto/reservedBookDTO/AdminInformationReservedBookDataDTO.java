package com.nye.myWay.dto.reservedBookDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminInformationReservedBookDataDTO {

    private String isbn;
    private String title;
    private LocalDateTime createdAt;
}
