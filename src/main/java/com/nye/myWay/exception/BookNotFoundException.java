package com.nye.myWay.exception;

import org.springframework.http.HttpStatus;

public class BookNotFoundException extends MyWayException {

    public BookNotFoundException() {
        super("The book with this ID doesn't exist", HttpStatus.NOT_FOUND);
    }
}
