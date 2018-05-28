package com.purko.url.shortener.exceptions;

public class NotValidUrlException extends RuntimeException{

    public NotValidUrlException() {
    }

    public NotValidUrlException(String message) {
        super(message);
    }

    public NotValidUrlException(String message, Throwable cause) {
        super(message, cause);
    }


}
