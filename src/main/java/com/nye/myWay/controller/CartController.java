package com.nye.myWay.controller;

import com.nye.myWay.dto.BookResponseUserDTO;
import com.nye.myWay.dto.CartDTO;
import com.nye.myWay.dto.CartReservedBookDTO;
import com.nye.myWay.dto.CartResponseUserDTO;
import com.nye.myWay.exception.MyWayException;
import com.nye.myWay.exception.NotEnoughBookException;
import com.nye.myWay.services.CartItemService;
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

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/add")
    public ResponseEntity<?> addBookToCart(@RequestBody CartDTO cartDTO, Principal principal) {
        try {
            BookResponseUserDTO bookResponseUserDTO = cartService.addBookToCart(cartDTO, principal);
            CartResponseUserDTO cartResponseUserDTO = new CartResponseUserDTO("Books added to cart successfully", bookResponseUserDTO);
            return ResponseEntity.ok(cartResponseUserDTO);
        } catch (NotEnoughBookException notEnoughBookException) {
            String errorMessage = notEnoughBookException.getMessage() + notEnoughBookException.getAvailableQuantity();
            return ResponseEntity.status(notEnoughBookException.getStatus()).body(errorMessage);
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(myWayException.getMessage());
        }
    }
    @PostMapping("/increase/{id}")
    public ResponseEntity<?> increaseIssueInCart(@PathVariable("id") Long bookId, Principal principal) {
        try {
            BookResponseUserDTO bookResponseUserDTO = cartItemService.increaseCartItemQuantityByButton(bookId, principal);
            CartResponseUserDTO cartResponseUserDTO = new CartResponseUserDTO("Book items increased successfully", bookResponseUserDTO);
            return ResponseEntity.ok(cartResponseUserDTO);
        } catch (NotEnoughBookException notEnoughBookException) {
            String errorMessage = notEnoughBookException.getMessage() + notEnoughBookException.getAvailableQuantity();
            return ResponseEntity.status(notEnoughBookException.getStatus()).body(errorMessage);
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(myWayException.getMessage());
        }
    }
}
