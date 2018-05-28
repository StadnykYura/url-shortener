package com.purko.url.shortener.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class is used as DTO to receive original url which should be shortened
 */
public class UrlToShorten {
    private String urlToShorten;

    @JsonCreator
    public UrlToShorten(@JsonProperty(value = "urlToShorten", required = true) String urlToShorten) {
        this.urlToShorten = urlToShorten;
    }

    public String getUrlToShorten() {
        return urlToShorten;
    }

    public void setUrlToShorten(String urlToShorten) {
        this.urlToShorten = urlToShorten;
    }

    @Override
    public String toString() {
        return "UrlToShorten{" +
                "urlToShorten='" + urlToShorten + '\'' +
                '}';
    }
}
