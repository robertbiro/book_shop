package com.nye.myWay.services;

import com.nye.myWay.dto.BookResponseUserDTO;
import com.nye.myWay.dto.CartDTO;
import com.nye.myWay.dto.CartReservedBookDTO;
import com.nye.myWay.entities.Cart;
import com.nye.myWay.exception.BookNotFoundException;
import com.nye.myWay.exception.NotEnoughBookException;
import com.nye.myWay.exception.UserNotFoundException;

import java.security.Principal;
import java.util.Optional;

public interface CartService {

    BookResponseUserDTO addBookToCart(CartDTO cartDTO, Principal principal) throws UserNotFoundException, BookNotFoundException, NotEnoughBookException;
    Optional<Cart> findCartByUserId(Long userId);
}
