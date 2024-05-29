package com.nye.myWay.controller;

import com.nye.myWay.dto.BookDTO;
import com.nye.myWay.entities.Book;
import com.nye.myWay.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/books")
@RequiredArgsConstructor
public class AdminBookController {

    private final BookService bookService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> addNewBook (@RequestBody BookDTO bookDTO) {
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable(name = "id") Long id,
                           @RequestBody BookDTO bookDTO) {
       return null;
    }
}
