package com.nye.myWay.controller;

import com.nye.myWay.dto.BookDTO;
import com.nye.myWay.dto.BookPaginationRequestDTO;
import com.nye.myWay.exception.MyWayException;
import com.nye.myWay.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/home")
@CrossOrigin("*")
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private final BookService bookService;

    @PostMapping
    //https://dev.to/brunbs/how-to-return-paginated-data-in-spring-boot-11cm
    ResponseEntity<Page<BookDTO>> getFilteredBooks(@RequestBody BookPaginationRequestDTO paginationRequest) {
        int page = paginationRequest.getPage() != null ? paginationRequest.getPage() : 0;
        int size = paginationRequest.getSize() != null ? paginationRequest.getSize() : 3;
        String direction = paginationRequest.getDirection() != null && !paginationRequest.getDirection().isEmpty() ? paginationRequest.getDirection() : "ASC";
        String orderBy = paginationRequest.getOrderBy() != null && !paginationRequest.getOrderBy().isEmpty() ? paginationRequest.getOrderBy() : "title";
        try {
            Page<BookDTO> filteredBooks = bookService.getFilteredBook(page, size, direction, orderBy);
            return ResponseEntity.ok().body(filteredBooks);
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(null);
        }
    }

}
