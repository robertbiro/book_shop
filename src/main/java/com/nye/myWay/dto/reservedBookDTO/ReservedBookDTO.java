package com.nye.myWay.dto.reservedBookDTO;

import com.nye.myWay.dto.cartItemDTOs.BookResponseUserDTO;
import com.nye.myWay.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservedBookDTO {
    private BookResponseUserDTO bookResponseUserDTO;
    private LocalDateTime createdAt;
}
