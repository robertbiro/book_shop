package com.nye.myWay.dto.projections;

import com.nye.myWay.dto.cartItemDTOs.BookResponseUserDTO;
import com.nye.myWay.entities.Book;

import java.time.LocalDateTime;

//comes from: https://medium.com/javarevisited/mastering-projections-in-spring-data-jpa-a-comprehensive-introduction-7bc2e64e4c14
public interface ReservedBookProjection {

    Book getBook();
    LocalDateTime getCreatedAt();

}
