package com.nye.myWay.services;

import com.nye.myWay.dto.CartDTO;
import com.nye.myWay.dto.CartReservedBookDTO;
import com.nye.myWay.entities.ApplicationUser;
import com.nye.myWay.entities.Book;
import com.nye.myWay.entities.Cart;
import com.nye.myWay.entities.CartItem;
import com.nye.myWay.exception.BookNotFoundException;
import com.nye.myWay.exception.NotEnoughBookException;
import com.nye.myWay.exception.UserNotFoundException;
import com.nye.myWay.repositories.BookRepository;
import com.nye.myWay.repositories.CartItemRepository;
import com.nye.myWay.repositories.CartRepository;
import com.nye.myWay.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    @Lazy
    //In contrast, when we configure a bean with lazy initialization,
    // it will only be created, and its dependencies will be injected once needed.
    //https://www.baeldung.com/spring-boot-lazy-initialization
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    @Lazy
    private CartItemService cartItemService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    //CartItem means a "wishes" or a reservation, so not an order yet.
    //Cart belongs to the user, every cart can contain more CartItem
    @Override
    public CartReservedBookDTO addBookToCart(CartDTO cartDTO, Principal principal) throws UserNotFoundException, BookNotFoundException, NotEnoughBookException {
        ApplicationUser applicationUser = userRepository.findByUsername(principal.getName()).orElseThrow(() ->new UserNotFoundException());
        Long userId = applicationUser.getId();
        Book reservedBook = bookRepository.findById(cartDTO.getBookId()).orElseThrow(() ->new BookNotFoundException());
        if (bookService.isBookAvailable(cartDTO.getBookId(), cartDTO.getQuantity())) {
            //dynamic approach!!!!!!!!!!!!!!!!!!!!
            Cart cart = cartRepository.findCartByUserId(applicationUser.getId());
            //If user hasn't Cart (and CartItem)
            if (cart == null) {
                cart = new Cart();
                cart.setApplicationUser(applicationUser);
                applicationUser.setCart(cart);
            }
            //If user has Cart and CartItem AND the bookId exists in CartItem -> if push the "Add to the Cart" button again
            if(cartItemService.userHasSameBookInCartItem(cartDTO.getBookId(),userId) != null) {
                Long cartId = cartRepository.findCartByUserId(userId).getId();
                Long cartItemId = cartItemRepository.findItemByCartIdAndBookId(cartId, cartDTO.getBookId()).get().getId();
                cartItemService.increaseCartItemQuantity(cartItemId);
            //If user has Cart and CartItem BUT the bookId doesn't exist in CartItem
            } else {
                CartItem cartItem = cartItemService.createCartItem(cartDTO);
                cartItem.setCart(cart);
                cart.getBooksInCart().add(cartItem);
                cartRepository.save(cart);
            }
            //To response DTO:
            CartReservedBookDTO cartReservedBookDTO = new CartReservedBookDTO();
            cartReservedBookDTO.setTitle(reservedBook.getTitle());
            cartReservedBookDTO.setIsbn(reservedBook.getIsbn());
            cartReservedBookDTO.setQuantity(cartItemService.getCurrentBookQuantityOfUser(cartDTO.getBookId(), userId));
            return cartReservedBookDTO;
        } else {
            throw new NotEnoughBookException();
        }
    }

    @Override
    public Cart findCartByUserId(Long userId) {
        return cartRepository.findCartByUserId(userId);
    }
}
