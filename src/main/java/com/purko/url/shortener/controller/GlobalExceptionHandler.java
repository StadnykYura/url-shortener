package com.purko.url.shortener.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.purko.url.shortener.exceptions.BadHashingAlgorithmException;
import com.purko.url.shortener.exceptions.NoDataFoundExceptionInDB;
import com.purko.url.shortener.exceptions.NotValidUrlException;
import com.purko.url.shortener.exceptions.RedirectingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global Exception Handler to handle all of the possible exception in app
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotValidUrlException.class, JsonMappingException.class})
    public ResponseEntity handleNotValidUrl(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoDataFoundExceptionInDB.class})
    public ResponseEntity handleNoDataFoundException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadHashingAlgorithmException.class})
    public ResponseEntity handleBadHashingAlgoException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({RedirectingException.class})
    public ResponseEntity handleRedirectiongException(Exception e) {
        return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles any exceptions within app
     * @param e Exception
     * @return ResponseEntity object
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity handleAnyException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
