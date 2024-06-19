package com.nye.myWay.exception;

import org.springframework.http.HttpStatus;

public class NotEnoughBookException extends MyWayException{

    public NotEnoughBookException() {
        super("Not enough books in the storage", HttpStatus.NOT_FOUND);
    }
}
