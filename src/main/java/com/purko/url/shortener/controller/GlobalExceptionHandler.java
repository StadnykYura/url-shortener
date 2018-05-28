package com.purko.url.shortener.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.purko.url.shortener.exceptions.BadHashingAlgorithmException;
import com.purko.url.shortener.exceptions.NoDataFoundExceptionInDB;
import com.purko.url.shortener.exceptions.NotValidUrlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleAnyException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({BadHashingAlgorithmException.class})
    public ResponseEntity handleBadHashingAlgoException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
