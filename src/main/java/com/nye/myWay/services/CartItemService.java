package com.nye.myWay.services;

import com.nye.myWay.dto.CartDTO;
import com.nye.myWay.entities.CartItem;

public interface CartItemService {

    CartItem createCartItem(CartDTO cartDTO);
    int getSameBookQuantityInCartItems(Long bookId);
    Long userHasSameBookInCartItem(Long bookId, Long userId);
    CartItem getCartItem(Long cartItemId);
    void increaseCartItemQuantity(Long cartItemId);
    Integer getCurrentBookQuantityOfUser(Long bookId, Long userId);

    void decreaseCartItemQuantity(Long cartItemId);
}
