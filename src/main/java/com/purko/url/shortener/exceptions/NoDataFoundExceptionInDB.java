package com.purko.url.shortener.exceptions;

public class NoDataFoundExceptionInDB extends RuntimeException {

    public NoDataFoundExceptionInDB() {
    }

    public NoDataFoundExceptionInDB(String message) {
        super(message);
    }

    public NoDataFoundExceptionInDB(String message, Throwable cause) {
        super(message, cause);
    }

}
