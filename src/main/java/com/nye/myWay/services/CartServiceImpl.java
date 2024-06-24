package com.nye.myWay.services;

import com.nye.myWay.dto.BookResponseUserDTO;
import com.nye.myWay.dto.CartDTO;
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
import java.util.Optional;

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
    @Lazy
    //In contrast, when we configure a bean with lazy initialization,
    // it will only be created, and its dependencies will be injected once needed.
    //https://www.baeldung.com/spring-boot-lazy-initialization
    private CartItemService cartItemService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Cart belongs to the user, every cart can contain more CartItem
    //CartItem means a "wishes" or a reservation, so not an order yet.
    @Override
    public BookResponseUserDTO addBookToCart(CartDTO cartDTO, Principal principal) throws UserNotFoundException, BookNotFoundException, NotEnoughBookException {
        ApplicationUser applicationUser = userRepository.findByUsername(principal.getName()).orElseThrow(() ->new UserNotFoundException());
        Long userId = applicationUser.getId();
        Book reservedBook = bookService.getBook(cartDTO.getBookId());
        int availableQuantity = reservedBook.getQuantity() - cartItemService.getSameBookQuantityInCartItems(cartDTO.getBookId());
        if (bookService.isBookAvailable(cartDTO.getBookId(), cartDTO.getQuantity())) {
            //dynamic approach!!!!!!!!!!!!!!!!!!!!
            Optional<Cart> optionalCart = cartRepository.findCartByUserId(userId);
            Cart cart;
            if (optionalCart.isPresent()) {
                cart = optionalCart.get();
            } else {
                //If user hasn't Cart (and CartItem)
                cart = new Cart();
                cart.setApplicationUser(applicationUser);
                applicationUser.setCart(cart);
                cartRepository.save(cart);
            }
            //If user has Cart and CartItem AND the bookId exists in CartItem -> if push the "Add to the Cart" button again
            if(cartItemService.userHasSameBookInCartItem(cartDTO.getBookId(),userId) != null) {
                Long cartId = cart.getId();
                Long cartItemId = cartItemRepository.findItemByCartIdAndBookId(cartId, cartDTO.getBookId()).get().getId();
                cartItemService.increaseCartItemQuantityByCart(cartItemId);
            //If user has Cart and CartItem BUT the bookId doesn't exist in CartItem
            } else {
                CartItem cartItem = cartItemService.createCartItem(cartDTO);
                cartItem.setCart(cart);
                cart.getBooksInCart().add(cartItem);
                cartRepository.save(cart);
            }
            //To response DTO about the last activity with Cart (so it is not the content of Cart!!!):
            BookResponseUserDTO bookResponseUserDTO = modelMapper.map(reservedBook, BookResponseUserDTO.class);
            bookResponseUserDTO.setQuantity(cartItemService.getCurrentBookQuantityOfUser(cartDTO.getBookId(), userId));
            return bookResponseUserDTO;
        } else {
            throw new NotEnoughBookException(availableQuantity);
        }
    }

    @Override
    public Optional<Cart> findCartByUserId(Long userId) {
        return cartRepository.findCartByUserId(userId);
    }
}
