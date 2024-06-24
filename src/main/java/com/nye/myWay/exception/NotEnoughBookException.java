package com.nye.myWay.exception;

import org.springframework.http.HttpStatus;

public class NotEnoughBookException extends MyWayException{
    private final int availableQuantity;

    public NotEnoughBookException(int availableQuantity) {
        super("Not enough books in the storage. Available quantity: " + availableQuantity, HttpStatus.NOT_FOUND);
        this.availableQuantity = availableQuantity;
    }
    public int getAvailableQuantity() {
        return availableQuantity;
    }
}
