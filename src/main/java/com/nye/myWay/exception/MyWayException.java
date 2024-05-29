package com.nye.myWay.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MyWayException extends Exception{

    private final HttpStatus status;

    public MyWayException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public MyWayException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public MyWayException(HttpStatus status) {
        super();
        this.status = status;
    }
}
