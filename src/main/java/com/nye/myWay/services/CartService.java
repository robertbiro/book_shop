package com.nye.myWay.services;

import com.nye.myWay.entities.Book;
import com.nye.myWay.repositories.CartRepository;

public interface CartService {

    void addToCart(Long bookId, Long UserId);

}
