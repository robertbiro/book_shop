package com.nye.myWay.controller;

import com.nye.myWay.dto.BookDTO;
import com.nye.myWay.dto.BookResponseDTO;
import com.nye.myWay.exception.MyWayException;
import com.nye.myWay.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/books")
@RequiredArgsConstructor
public class AdminBookController {

    @Autowired
    private final BookService bookService;
    // token -> https://jwt.io/

    @PostMapping("/add")
    public ResponseEntity<?> addNewBook(@RequestBody BookDTO bookDTO) {
        BookDTO newBookDTO = bookService.addBook(bookDTO);
        System.out.println("HEllo");
        BookResponseDTO bookResponseDTO = new BookResponseDTO("Book added successfully", newBookDTO);
        //https://codingnomads.com/spring-responseentity
        //return new ResponseEntity<>(bookResponseDTO, HttpStatus.CREATED);
        return ResponseEntity.ok(bookResponseDTO);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable(name = "id") Long bookId,
                                        @RequestBody BookDTO bookDTO) {
        try {
            System.out.println("Heello from put");
            BookDTO updatedBookDTO = bookService.updateBook(bookId, bookDTO);
            BookResponseDTO bookResponseDTO = new BookResponseDTO("Book is updated successfully", updatedBookDTO);
            return ResponseEntity.ok(bookResponseDTO);
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(myWayException.getMessage());
        }
    }
}
