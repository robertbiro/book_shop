package com.nye.myWay.services;

import com.nye.myWay.dto.cartItemDTOs.BookResponseUserDTO;
import com.nye.myWay.dto.cartDTOs.CartDTO;
import com.nye.myWay.dto.cartItemDTOs.DecreaseCartItemDTO;
import com.nye.myWay.entities.CartItem;
import com.nye.myWay.exception.*;

import java.security.Principal;
import java.util.List;

public interface CartItemService {

    CartItem createCartItem(CartDTO cartDTO);
    int getSameBookQuantityInCartItems(Long bookId);
    CartItem getItemByCartIdAndBookId(Long cartId, Long bookId) throws CartItemNotFoundException;
    Long getCartItemIAtSameBook(Long bookId, Long userId) throws CartItemNotFoundException;
    CartItem getCartItem(Long cartItemId);
    void increaseCartItemQuantityByCart(Long cartItemId);
    BookResponseUserDTO increaseCartItemQuantityByButton(Long bookId, Principal principal) throws UserNotFoundException, BookNotFoundException, CartNotFoundException, NotEnoughBookException, CartItemNotFoundException;
    Integer getCurrentBookQuantityOfUser(Long bookId, Long userId) throws CartItemNotFoundException;
    DecreaseCartItemDTO decreaseCartItemQuantityByButton(Long bookId, Principal principal) throws UserNotFoundException, BookNotFoundException, CartNotFoundException, CartItemNotFoundException;
    DecreaseCartItemDTO deleteCartItem(Long cartItemId) throws BookNotFoundException, CartItemNotFoundException;
    List<Long> findAllBookIdByUserId(Long userId);
    Long findItemByCartIdAndBookId(Long cartId, Long bookId);
}
