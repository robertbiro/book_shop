package com.nye.myWay.exception;

import org.springframework.http.HttpStatus;

public class UserNameMissingException extends MyWayException{

    public UserNameMissingException() {
        super("Missing username!", HttpStatus.BAD_REQUEST);
    }
}
