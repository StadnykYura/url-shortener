package com.purko.url.shortener.exceptions;

public class RedirectingException extends RuntimeException {
    public RedirectingException() {
    }

    public RedirectingException(String message) {
        super(message);
    }

    public RedirectingException(String message, Throwable cause) {
        super(message, cause);
    }
}
