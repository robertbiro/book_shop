package com.nye.myWay.services;

import com.nye.myWay.dto.CartDTO;
import com.nye.myWay.dto.CartReservedBookDTO;
import com.nye.myWay.entities.Book;
import com.nye.myWay.entities.Cart;
import com.nye.myWay.exception.BookNotFoundException;
import com.nye.myWay.exception.NotEnoughBookException;
import com.nye.myWay.exception.UserNotFoundException;
import com.nye.myWay.repositories.CartRepository;

import java.security.Principal;

public interface CartService {

    CartReservedBookDTO addBookToCart(CartDTO cartDTO, Principal principal) throws UserNotFoundException, BookNotFoundException, NotEnoughBookException;
    Cart findCartByUserId(Long userId);
}
