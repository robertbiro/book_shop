package com.nye.myWay.controller;

import com.nye.myWay.dto.CartDTO;
import com.nye.myWay.dto.CartReservedBookDTO;
import com.nye.myWay.dto.CartResponseDTO;
import com.nye.myWay.exception.BookNotFoundException;
import com.nye.myWay.exception.MyWayException;
import com.nye.myWay.exception.NotEnoughBookException;
import com.nye.myWay.exception.UserNotFoundException;
import com.nye.myWay.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user/cart")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<?> addBookToCart(@RequestBody CartDTO cartDTO, Principal principal) {
        try {
            System.out.println(cartDTO.getBookId());
            CartReservedBookDTO cartReservedBookDTO = cartService.addBookToCart(cartDTO, principal);
            CartResponseDTO cartResponseDTO = new CartResponseDTO("Books added to cart successfully", cartReservedBookDTO);
            return ResponseEntity.ok(cartResponseDTO);
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(myWayException.getMessage());
        }
    }
}
