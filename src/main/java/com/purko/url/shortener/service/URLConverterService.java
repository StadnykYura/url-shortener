package com.purko.url.shortener.service;

import javax.servlet.http.HttpServletRequest;

public interface URLConverterService {

    String fromOriginalUrlToShorten(String originalClientUrl, HttpServletRequest request);
    String fromShortenUrlToOriginal(String uniqueKeyForOriginalURL);

}
