package com.nye.myWay.exception;

import org.springframework.http.HttpStatus;

public class AvailableBookInStockException extends MyWayException {

    public AvailableBookInStockException() {
        super("The book is available in stock", HttpStatus.NOT_FOUND);
    }
}
