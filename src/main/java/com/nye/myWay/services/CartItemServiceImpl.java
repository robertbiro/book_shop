package com.nye.myWay.services;

import com.nye.myWay.dto.BookResponseUserDTO;
import com.nye.myWay.dto.CartDTO;
import com.nye.myWay.entities.ApplicationUser;
import com.nye.myWay.entities.Book;
import com.nye.myWay.entities.Cart;
import com.nye.myWay.entities.CartItem;
import com.nye.myWay.exception.BookNotFoundException;
import com.nye.myWay.exception.CartNotFoundException;
import com.nye.myWay.exception.NotEnoughBookException;
import com.nye.myWay.exception.UserNotFoundException;
import com.nye.myWay.repositories.CartItemRepository;
import com.nye.myWay.repositories.CartRepository;
import com.nye.myWay.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    @Lazy
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

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
        Long cartId = cartService.findCartByUserId(userId).get().getId();
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
    public void increaseCartItemQuantityByCart(Long cartItemId) {
        CartItem cartItem = getCartItem(cartItemId);
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItemRepository.save(cartItem);
    }

    @Override
    public BookResponseUserDTO increaseCartItemQuantityByButton(Long bookId, Principal principal) throws UserNotFoundException, BookNotFoundException, CartNotFoundException, NotEnoughBookException {
        ApplicationUser applicationUser = userRepository.findByUsername(principal.getName()).orElseThrow(() ->new UserNotFoundException());
        Optional<Cart> optionalCart = cartRepository.findCartByUserId(applicationUser.getId());
        if(optionalCart.isPresent()) {
            Long cartId = cartService.findCartByUserId(applicationUser.getId()).get().getId();
            Long cartItemId = cartItemRepository.findItemByCartIdAndBookId(cartId, bookId).get().getId();
            Book reservedBook;
            CartItem cartItem = getCartItem(cartItemId);
            if(bookService.isBookAvailable(bookId, 1)) {
                reservedBook = bookService.getBook(bookId);
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemRepository.save(cartItem);
                //To response DTO about the last activity with Cart (so it is not the content of Cart!!!):
                BookResponseUserDTO bookResponseUserDTO = modelMapper.map(reservedBook, BookResponseUserDTO.class);
                bookResponseUserDTO.setQuantity(cartItem.getQuantity());
                return bookResponseUserDTO;
            } else {
                reservedBook = bookService.getBook(bookId);
                int availableQuantity = reservedBook.getQuantity() - getSameBookQuantityInCartItems(bookId);
                throw new NotEnoughBookException(availableQuantity);
            }
        } else {
            throw new CartNotFoundException();
        }
    }

    @Override
    public Integer getCurrentBookQuantityOfUser(Long bookId, Long userId) {
        Long cartId = cartService.findCartByUserId(userId).get().getId();
        Optional<CartItem> cartItem = cartItemRepository.findItemByCartIdAndBookId(cartId, bookId);
        return cartItem.get().getQuantity();
    }

    @Override
    public void decreaseCartItemQuantity(Long cartItemId) {

    }

    @Override
    public List<Long> findAllBookIdByUserId(Long userId) {
        return cartItemRepository.findAllBookIdByUserId(userId);
    }
}
