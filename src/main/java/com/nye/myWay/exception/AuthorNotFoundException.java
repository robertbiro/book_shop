package com.nye.myWay.exception;

import org.springframework.http.HttpStatus;

public class AuthorNotFoundException extends MyWayException {

    public AuthorNotFoundException() {
        super("The author doesn't exist", HttpStatus.NOT_FOUND);
    }
}
