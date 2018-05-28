package com.purko.url.shortener.controller;


import com.purko.url.shortener.dto.UrlToShorten;
import com.purko.url.shortener.exceptions.BadHashingAlgorithmException;
import com.purko.url.shortener.exceptions.NoDataFoundExceptionInDB;
import com.purko.url.shortener.exceptions.NotValidUrlException;
import com.purko.url.shortener.service.URLConverterService;
import com.purko.url.shortener.util.Constants;
import com.purko.url.shortener.validator.UrlToShortenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
public class URLController {

    @Autowired
    URLConverterService urlConverterService;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(new UrlToShortenValidator());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage() {
        return "Hello, My name is Spring Boot";
    }

    @RequestMapping(value = "/to-shorten", method = RequestMethod.POST)
    public ResponseEntity<String> toShorten(@RequestBody @Valid UrlToShorten urlToShorten, HttpServletRequest request) throws NoDataFoundExceptionInDB, BadHashingAlgorithmException{

        String shortenedUrl = urlConverterService.fromOriginalUrlToShorten(urlToShorten.getUrlToShorten(), request);
        if (shortenedUrl.length() == 0) {
            throw new BadHashingAlgorithmException(Constants.BAD_HASHING_ALGORITHM);
        }

        return new ResponseEntity<>(shortenedUrl, HttpStatus.OK);
    }

    @RequestMapping(value = "/{uniqueKey}", method = RequestMethod.GET)
    public void redirectToOriginal(@PathVariable String uniqueKey, HttpServletResponse response) throws IOException {

        String originalUrl = urlConverterService.fromShortenUrlToOriginal(uniqueKey);
        if (originalUrl.length() == 0) {
            throw new NoDataFoundExceptionInDB(Constants.NO_DATA_FOUND_IN_DB);
        }

        response.sendRedirect(originalUrl);
    }
}
