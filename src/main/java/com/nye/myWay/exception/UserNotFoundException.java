package com.nye.myWay.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends MyWayException{

    public UserNotFoundException() {
        super("The user is not registered yet", HttpStatus.NOT_FOUND);
    }
}
