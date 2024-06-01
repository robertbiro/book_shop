package com.nye.myWay.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.Objects;

@Data
public class BookPaginationRequestDTO {

    private Integer pageNo = 0;
    private Integer pageSize = 10;

    public Pageable getPageable(BookPaginationRequestDTO bookPaginationRequestDTO) {
        Integer page =  Objects.nonNull(bookPaginationRequestDTO.getPageNo()) ? bookPaginationRequestDTO.pageNo : this.pageNo;
    return null;
    }


}
