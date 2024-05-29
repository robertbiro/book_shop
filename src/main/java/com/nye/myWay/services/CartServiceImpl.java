package com.nye.myWay.services;

import com.nye.myWay.entities.Book;
import com.nye.myWay.entities.Cart;
import com.nye.myWay.repositories.BookRepository;
import com.nye.myWay.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;
    private UserService userService;
    private BookRepository bookRepository;

    @Override
    public void addToCart(Long bookId, Long UserId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        //List<Cart> cartsWithSearchedBooks = cartRepository.findByBooksId(bookId);
        //int totalSearchedBooksInCarts = countSearchedBooksInCarts(cartsWithSearchedBooks, bookId);
        //int availableQuantity = book.getQuantity() - totalSearchedBooksInCarts;
        //if (availableQuantity > 0) {
        //}



    }

    private int countSearchedBooksInCarts(List<Cart> carts, Long bookId) {
        int totalSearchedBooks = 0;
        for (Cart cart : carts) {
            totalSearchedBooks += cart.getBooks().stream()
                    .filter(book -> book.getId().equals(bookId))
                    .count();
        }
        return totalSearchedBooks;
    }
}
