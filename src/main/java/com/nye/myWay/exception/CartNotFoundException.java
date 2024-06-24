package com.nye.myWay.exception;

import org.springframework.http.HttpStatus;

public class CartNotFoundException extends MyWayException{
    public CartNotFoundException() {
        super("The cart with this ID doesn't exist", HttpStatus.NOT_FOUND);
    }
}
