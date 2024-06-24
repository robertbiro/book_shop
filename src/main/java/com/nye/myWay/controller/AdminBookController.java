package com.nye.myWay.controller;

import com.nye.myWay.dto.BookResponseAdminDTO;
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
    public ResponseEntity<?> addNewBook(@RequestBody BookResponseAdminDTO bookResponseAdminDTO) {
        BookResponseAdminDTO newBookResponseAdminDTO = bookService.addBook(bookResponseAdminDTO);
        System.out.println("Hello from add...........");
        BookResponseDTO bookResponseDTO = new BookResponseDTO("Book added successfully", newBookResponseAdminDTO);
        //https://codingnomads.com/spring-responseentity
        //return new ResponseEntity<>(bookResponseDTO, HttpStatus.CREATED);
        return ResponseEntity.ok(bookResponseDTO);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable(name = "id") Long bookId,
                                        @RequestBody BookResponseAdminDTO bookResponseAdminDTO) {
        try {
            System.out.println("Hello from put!!!!!");
            BookResponseAdminDTO updatedBookResponseAdminDTO = bookService.updateBook(bookId, bookResponseAdminDTO);
            BookResponseDTO bookResponseDTO = new BookResponseDTO("Book is updated successfully", updatedBookResponseAdminDTO);
            return ResponseEntity.ok(bookResponseDTO);
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(myWayException.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(name = "id") Long bookId) {
        try {
            BookResponseAdminDTO deletedBookResponseAdminDTO = bookService.deleteBook(bookId);
            BookResponseDTO bookResponseDTO = new BookResponseDTO("Book is deleted successfully", deletedBookResponseAdminDTO);
            return ResponseEntity.ok(bookResponseDTO);
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(myWayException.getMessage());
        }
    }
}
