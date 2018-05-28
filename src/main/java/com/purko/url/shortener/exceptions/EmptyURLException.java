package com.purko.url.shortener.exceptions;

public class EmptyURLException extends RuntimeException {

    public EmptyURLException() {
    }

    public EmptyURLException(String message) {
        super(message);
    }

    public EmptyURLException(String message, Throwable cause) {
        super(message, cause);
    }

}
