package com.nye.myWay.services;

import com.nye.myWay.dto.BookResponseUserDTO;
import com.nye.myWay.dto.CartDTO;
import com.nye.myWay.entities.CartItem;
import com.nye.myWay.exception.BookNotFoundException;
import com.nye.myWay.exception.CartNotFoundException;
import com.nye.myWay.exception.NotEnoughBookException;
import com.nye.myWay.exception.UserNotFoundException;

import java.security.Principal;

public interface CartItemService {

    CartItem createCartItem(CartDTO cartDTO);
    int getSameBookQuantityInCartItems(Long bookId);
    Long userHasSameBookInCartItem(Long bookId, Long userId);
    CartItem getCartItem(Long cartItemId);
    void increaseCartItemQuantityByCart(Long cartItemId);
    BookResponseUserDTO increaseCartItemQuantityByButton(Long bookId, Principal principal) throws UserNotFoundException, BookNotFoundException, CartNotFoundException, NotEnoughBookException;
    Integer getCurrentBookQuantityOfUser(Long bookId, Long userId);
    void decreaseCartItemQuantity(Long cartItemId);
}
