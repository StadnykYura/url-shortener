package com.purko.url.shortener.exceptions;

/**
 * custom exception to send client
 */
public class BadHashingAlgorithmException extends RuntimeException{

    public BadHashingAlgorithmException() {
    }

    public BadHashingAlgorithmException(String message) {
        super(message);
    }

    public BadHashingAlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }
}
