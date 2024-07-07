package com.nye.myWay.exception;

import org.springframework.http.HttpStatus;

public class DuplicateReservationException extends MyWayException {

    public DuplicateReservationException() {
        super("You have already reserved this book.", HttpStatus.NOT_FOUND);
    }
}
