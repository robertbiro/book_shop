package com.nye.myWay.controller;

import com.nye.myWay.dto.*;
import com.nye.myWay.dto.cartDTOs.CartDTO;
import com.nye.myWay.dto.cartDTOs.CartResponseUserAllBookDTO;
import com.nye.myWay.dto.cartDTOs.CartResponseUserOneBookDTO;
import com.nye.myWay.dto.cartItemDTOs.*;
import com.nye.myWay.exception.MyWayException;
import com.nye.myWay.exception.NotEnoughBookException;
import com.nye.myWay.services.CartItemService;
import com.nye.myWay.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
            CartResponseUserOneBookDTO cartResponseUserOneBookDTO = new CartResponseUserOneBookDTO("Books added to cart successfully", bookResponseUserDTO);
            return ResponseEntity.ok(cartResponseUserOneBookDTO);
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
            CartResponseUserOneBookDTO cartResponseUserOneBookDTO = new CartResponseUserOneBookDTO("Book items increased successfully", bookResponseUserDTO);
            return ResponseEntity.ok(cartResponseUserOneBookDTO);
        } catch (NotEnoughBookException notEnoughBookException) {
            String errorMessage = notEnoughBookException.getMessage() + notEnoughBookException.getAvailableQuantity();
            return ResponseEntity.status(notEnoughBookException.getStatus()).body(errorMessage);
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(myWayException.getMessage());
        }
    }

    @PostMapping("/decrease/{id}")
    public ResponseEntity<?> decreaseIssueInCart(@PathVariable("id") Long bookId, Principal principal) {
        try {
            DecreaseCartItemDTO decreaseCartItemDTO = cartItemService.decreaseCartItemQuantityByButton(bookId, principal);
            if (!decreaseCartItemDTO.isDelete()) {
                return ResponseEntity.ok(new CartItemChangeDTO<>("Book items decreased successfully",decreaseCartItemDTO));
            } else {
                return ResponseEntity.ok(new CartItemChangeDTO<>("CartItem is deleted", null));
            }
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(myWayException.getMessage());
        }
    }

    @GetMapping("/getCartContent")
    public ResponseEntity<?> getCartContent(Principal principal) {
        try {
            List<BookResponseUserDTO> allBookInCartByUser = cartService.getCartContentByUser(principal);
            CartResponseUserAllBookDTO cartResponseUserAllBookDTO = new CartResponseUserAllBookDTO("The current content of your Cart: ", allBookInCartByUser);
            return ResponseEntity.ok(cartResponseUserAllBookDTO);
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(myWayException.getMessage());
        }
    }
    @DeleteMapping("/deleteCart")
    public ResponseEntity<?> deleteCart(Principal principal) {
        try {
            cartService.deleteCart(principal);
            MessageDTO messageDTO = new MessageDTO("Successful.The Cart is empty.");
            return ResponseEntity.ok(messageDTO);
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(myWayException.getMessage());
        }
    }
}
