package com.nye.myWay.services;

import com.nye.myWay.dto.cartItemDTOs.BookResponseUserDTO;
import com.nye.myWay.dto.cartDTOs.CartDTO;
import com.nye.myWay.entities.Cart;
import com.nye.myWay.exception.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface CartService {

    BookResponseUserDTO addBookToCart(CartDTO cartDTO, Principal principal) throws UserNotFoundException, BookNotFoundException, NotEnoughBookException, CartItemNotFoundException;
    Optional<Cart> findCartByUserId(Long userId);
    List<BookResponseUserDTO> getCartContentByUser(Principal principal) throws UserNotFoundException, BookNotFoundException, CartNotFoundException, CartItemNotFoundException;
    void deleteCart(Principal principal) throws UserNotFoundException, CartNotFoundException, CartItemNotFoundException, BookNotFoundException;
}
