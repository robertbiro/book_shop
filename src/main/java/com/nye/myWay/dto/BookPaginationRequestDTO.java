package com.nye.myWay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPaginationRequestDTO {

    private Integer page;
    private Integer size;
    private String direction;
    protected String orderBy;

}
