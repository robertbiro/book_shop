package com.nye.myWay.exception;

import org.springframework.http.HttpStatus;

public class ReservedBookNotFoundException extends MyWayException {

    public ReservedBookNotFoundException() {
        super("The reserved book with this ID doesn't exist", HttpStatus.NOT_FOUND);
    }
}
