package com.nye.myWay.exception;

import org.springframework.http.HttpStatus;

public class UserNameAlreadyTakenException extends MyWayException {

    public UserNameAlreadyTakenException (){
        super("The username is already taken", HttpStatus.BAD_REQUEST);
    }
}
