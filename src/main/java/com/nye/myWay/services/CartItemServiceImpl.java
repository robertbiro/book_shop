package com.nye.myWay.services;

import com.nye.myWay.dto.CartDTO;
import com.nye.myWay.entities.Cart;
import com.nye.myWay.entities.CartItem;
import com.nye.myWay.repositories.CartItemRepository;
import com.nye.myWay.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartService cartService;

    @Override
    public CartItem createCartItem(CartDTO cartDTO) {
        CartItem cartItem = new CartItem();
        cartItem.setBookId(cartDTO.getBookId());
        cartItem.setQuantity(cartDTO.getQuantity());
        return cartItem;
    }

    @Override
    public int getSameBookQuantityInCartItems(Long bookId) {
        if (cartItemRepository.countBooksInCartsWithSameBookId(bookId) == null) {
            return 0;
        } else {
            return cartItemRepository.countBooksInCartsWithSameBookId(bookId);
        }
    }

    @Override
    public Long userHasSameBookInCartItem(Long bookId, Long userId) {
        Long cartId = cartService.findCartByUserId(userId).getId();
        Optional<CartItem> cartItem = cartItemRepository.findItemByCartIdAndBookId(cartId, bookId);
        if (cartItem.isPresent()) {
            return cartItem.get().getId();
        } else {
            return null;
        }
    }

    @Override
    public CartItem getCartItem(Long cartItemId) {
        return cartItemRepository.findById(cartItemId).get();
    }

    @Override
    public void increaseCartItemQuantity(Long cartItemId) {
        CartItem cartItem = getCartItem(cartItemId);
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItemRepository.save(cartItem);
    }

    @Override
    public Integer getCurrentBookQuantityOfUser(Long bookId, Long userId) {
        Long cartId = cartService.findCartByUserId(userId).getId();
        Optional<CartItem> cartItem = cartItemRepository.findItemByCartIdAndBookId(cartId, bookId);
        return cartItem.get().getQuantity();
    }

    @Override
    public void decreaseCartItemQuantity(Long cartItemId) {

    }
}
