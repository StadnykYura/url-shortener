package com.purko.url.shortener.controller;


import com.purko.url.shortener.dto.UrlToShorten;
import com.purko.url.shortener.exceptions.BadHashingAlgorithmException;
import com.purko.url.shortener.exceptions.NoDataFoundExceptionInDB;
import com.purko.url.shortener.exceptions.NotValidUrlException;
import com.purko.url.shortener.exceptions.RedirectingException;
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

/**
 * Controller to work with client requests
 */
@RestController
public class URLController {

    @Autowired
    URLConverterService urlConverterService;

    /**
     * Registering own custom validator for UrlToShorten object
     *
     * @see UrlToShortenValidator
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(new UrlToShortenValidator());
    }

    /**
     * Welcome return string for app
     * @return welcome string
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage() {
        return "Hello, My name is Spring Boot";
    }

    /**
     * Maps all requests to localhost:8080/url-shortener/to-shorten to this method and work with original url,
     * which should be shortened
     * @param urlToShorten DTO Object which contains original url to be shortened
     * @param request HttpServletRequest is used to get local serverName and context path
     * @return shortened url, which can be used to rich original url
     * @throws NotValidUrlException If received original url is not valid url representation
     * @throws BadHashingAlgorithmException if encode algorithm produce wrong key
     */
    @RequestMapping(value = "/to-shorten", method = RequestMethod.POST)
    public ResponseEntity<String> toShorten(@RequestBody @Valid UrlToShorten urlToShorten, HttpServletRequest request) throws BadHashingAlgorithmException{

        String shortenedUrl = urlConverterService.fromOriginalUrlToShorten(urlToShorten.getUrlToShorten(), request);
        if (shortenedUrl.length() == 0) {
            throw new BadHashingAlgorithmException(Constants.BAD_HASHING_ALGORITHM);
        }

        return new ResponseEntity<>(shortenedUrl, HttpStatus.OK);
    }

    /**
     *
     * @param uniqueKey base62 representation of some particular URL's 'id' value
     * @param response HttpServletResponse is used to redirect client to original
     * @throws RedirectingException if redirecting fails
     * @throws NoDataFoundExceptionInDB If no data is found in DB with this shortened url
     */
    @RequestMapping(value = "/{uniqueKey}", method = RequestMethod.GET)
    public void redirectToOriginal(@PathVariable String uniqueKey, HttpServletResponse response)  {

        String originalUrl = urlConverterService.fromShortenUrlToOriginal(uniqueKey);
        if (originalUrl.length() == 0) {
            throw new NoDataFoundExceptionInDB(Constants.NO_DATA_FOUND_IN_DB);
        }
        try {
            response.sendRedirect(originalUrl);
        } catch (IOException e) {
            throw new RedirectingException(Constants.PROBLEM_WITH_REDIRECTING, e);
        }
    }
}
