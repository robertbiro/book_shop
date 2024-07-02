package com.nye.myWay.services;

import com.nye.myWay.dto.cartItemDTOs.BookResponseUserDTO;
import com.nye.myWay.dto.cartDTOs.CartDTO;
import com.nye.myWay.dto.cartItemDTOs.DecreaseCartItemDTO;
import com.nye.myWay.entities.ApplicationUser;
import com.nye.myWay.entities.Book;
import com.nye.myWay.entities.Cart;
import com.nye.myWay.entities.CartItem;
import com.nye.myWay.exception.*;
import com.nye.myWay.repositories.CartItemRepository;
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
    private UserService userService;
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
    public CartItem getItemByCartIdAndBookId(Long cartId, Long bookId) throws CartItemNotFoundException {
        Optional<CartItem> optionalCartItem = cartItemRepository.findItemByCartIdAndBookId(cartId, bookId);
        if(optionalCartItem.isPresent()) {
            return optionalCartItem.get();
        } else {
            throw new CartItemNotFoundException();
        }
    }

    @Override
    public Long getCartItemIAtSameBook(Long bookId, Long userId) {
        Long cartId = cartService.findCartByUserId(userId).get().getId();
        return cartItemRepository.findItemIdByCartIdAndBookId(cartId, bookId);
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
    public BookResponseUserDTO increaseCartItemQuantityByButton(Long bookId, Principal principal) throws UserNotFoundException, BookNotFoundException, CartNotFoundException, NotEnoughBookException, CartItemNotFoundException {
        ApplicationUser applicationUser = userService.getUserByPrincipal(principal);
        Optional<Cart> optionalCart = cartService.findCartByUserId(applicationUser.getId());
        if(optionalCart.isPresent()) {
            Long cartId = cartService.findCartByUserId(applicationUser.getId()).get().getId();
            //// TODO: 2024. 06. 29. nedd an axception
            Long cartItemId = getItemByCartIdAndBookId(cartId, bookId).getId();
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
    public Integer getCurrentBookQuantityOfUser(Long bookId, Long userId) throws CartItemNotFoundException {
        Long cartId = cartService.findCartByUserId(userId).get().getId();
        CartItem cartItem = getItemByCartIdAndBookId(cartId, bookId);
        return cartItem.getQuantity();
    }

    @Override
    public DecreaseCartItemDTO decreaseCartItemQuantityByButton(Long bookId, Principal principal) throws UserNotFoundException, BookNotFoundException, CartNotFoundException, CartItemNotFoundException {
        ApplicationUser applicationUser = userService.getUserByPrincipal(principal);
        Optional<Cart> optionalCart = cartService.findCartByUserId(applicationUser.getId());
        if (optionalCart.isPresent()) {
            Long cartId = cartService.findCartByUserId(applicationUser.getId()).get().getId();
            Long cartItemId = getItemByCartIdAndBookId(cartId, bookId).getId();
            Book reservedBook = bookService.getBook(bookId);
            CartItem cartItem = getCartItem(cartItemId);
            if(cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartItemRepository.save(cartItem);
                //To response DTO about the last activity with Cart (so it is not the content of Cart!!!):
                BookResponseUserDTO bookResponseUserDTO = modelMapper.map(reservedBook, BookResponseUserDTO.class);
                bookResponseUserDTO.setQuantity(cartItem.getQuantity());
                return new DecreaseCartItemDTO<>(false, bookResponseUserDTO);
            } else {
                //if quantity is zero, the cartItem will be deleted
                return deleteCartItem(cartItemId);
            }
        }  else {
            throw new CartNotFoundException();
        }
    }
    //this method are used by "-" on frontend, and a button to delete an book from cart.
    @Override
    public DecreaseCartItemDTO deleteCartItem(Long cartItemId) throws CartItemNotFoundException {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);
        if(optionalCartItem.isPresent()) {
            cartItemRepository.delete(optionalCartItem.get());
            return new DecreaseCartItemDTO<>(true, null);
        } else {
            throw new CartItemNotFoundException();
        }
    }

    @Override
    public List<Long> findAllBookIdByUserId(Long userId) {
        return cartItemRepository.findAllBookIdByUserId(userId);
    }

    @Override
    public Long findItemByCartIdAndBookId(Long cartId, Long bookId) {
        return cartItemRepository.findItemByCartIdAndBookId(cartId, bookId).get().getId();
    }
}
