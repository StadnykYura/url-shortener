package com.purko.url.shortener.validator;

import com.purko.url.shortener.dto.UrlToShorten;
import com.purko.url.shortener.exceptions.EmptyURLException;
import com.purko.url.shortener.exceptions.NotValidUrlException;
import com.purko.url.shortener.util.Constants;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UrlToShortenValidator implements Validator {

    /*
    we can use our own regex
    or use UrlValidator from Apache Commons

    regex to test (because not all of them are ideal:
    a) _^(?:(?:https?|ftp)://)(?:\S+(?::\S*)?@)?(?:(?!10(?:\.\d{1,3}){3})(?!127(?:\.\d{1,3}){3})(?!169\.254(?:\.\d{1,3}){2})(?!192\.168(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\x{00a1}-\x{ffff}0-9]+-?)*[a-z\x{00a1}-\x{ffff}0-9]+)(?:\.(?:[a-z\x{00a1}-\x{ffff}0-9]+-?)*[a-z\x{00a1}-\x{ffff}0-9]+)*(?:\.(?:[a-z\x{00a1}-\x{ffff}]{2,})))(?::\d{2,5})?(?:/[^\s]*)?$_iuS
    b) ^(https?|ftp)://[^\s/$.?#].[^\s]*$
    c) ^(https?:\\/\\/)?(www\\.)?([\\w]+\\.)+[‌​\\w]{2,63}\\/?$
     */

    private static final String URL_REGEX = "^((https?|ftp)://)?[^\\s/$.?#].[^\\s]*$";
    private static final Pattern URL_PATTERN = Pattern.compile("^((https?|ftp)://)?[^\\s/$.?#].[^\\s]*$");

    @Override
    public boolean supports(Class<?> aClass) {
        return UrlToShorten.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) throws NotValidUrlException {

        UrlToShorten urlToShorten = (UrlToShorten) o;
        String url = urlToShorten.getUrlToShorten();
        if (url.isEmpty()) {
            throw new EmptyURLException(Constants.EMPTY_URL);
        }
        // approach with own regex
        Matcher m = URL_PATTERN.matcher(url);
        if (!m.matches()) {
            throw new NotValidUrlException(url + Constants.WRONG_URL_REPRESENTATION);
        }

        // approach with Apache commons.
//        UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
//        if (!urlValidator.isValid(url)){
//            throw new NotValidUrlException(Constants.WRONG_URL_REPRESENTATION);
//        }
    }
}
