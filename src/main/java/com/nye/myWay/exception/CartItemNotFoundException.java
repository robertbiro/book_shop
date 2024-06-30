package com.nye.myWay.exception;

import org.springframework.http.HttpStatus;

public class CartItemNotFoundException extends MyWayException{
    public CartItemNotFoundException() {
        super("The cartItem with this ID doesn't exist", HttpStatus.NOT_FOUND);
    }
}
