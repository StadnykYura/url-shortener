package com.purko.url.shortener.service.impl;

import com.purko.url.shortener.dao.URLDataRepository;
import com.purko.url.shortener.exceptions.NoDataFoundExceptionInDB;
import com.purko.url.shortener.model.UrlData;
import com.purko.url.shortener.service.URLConverterService;
import com.purko.url.shortener.util.Constants;
import com.purko.url.shortener.util.EncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Service
public class URLConverterServiceImpl implements URLConverterService {

    private final URLDataRepository urlDataRepository;

    @Autowired
    public URLConverterServiceImpl(URLDataRepository urlDataRepository) {
        this.urlDataRepository = urlDataRepository;
    }

    /**
     * Checks whether originalClientUrl exists in DB.
     * If so, returns shortened URL from existing originalClientUrl id.
     * Otherwise creates a new URL from originalClientUrl
     *
     * @param originalClientUrl an URL which is going to be shortened
     * @param request           Http request
     * @return shortened URL which is used to get originalClientUrl from particular domain;
     */
    @Override
    @Transactional
    public String fromOriginalUrlToShorten(String originalClientUrl, HttpServletRequest request) {
        StringBuilder shortenedUrl = new StringBuilder();
        String host = getHostName(request);
        originalClientUrl = cleanUrlFromScheme(originalClientUrl);

        UrlData managedUrlData = getManagedUrlData(originalClientUrl);

        String base62Key = EncodeUtil.fromBase10toBase62Encode(managedUrlData.getId());

        if (base62Key.length() == 0) {
            return "";
        }

        return shortenedUrl
                .append(host)
                .append("/")
                .append(base62Key).toString();
    }

    private UrlData getManagedUrlData(String originalClientUrl) {
        UrlData managedUrlData = new UrlData();
        Optional<UrlData> urlDataFromDb = urlDataRepository.findExistingOriginalUrl(originalClientUrl);
        if (urlDataFromDb.isPresent()) {
            managedUrlData = urlDataFromDb.get();
        } else {
            managedUrlData.setOriginalUrl(originalClientUrl);
        }
        managedUrlData.setUpdatedTime(new Date());
        if (managedUrlData.getId() == null) {
            managedUrlData = urlDataRepository.saveAndFlush(managedUrlData);
        } else {
            managedUrlData = urlDataRepository.save(managedUrlData);
        }
        return managedUrlData;
    }

    private String getHostName(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String url = request.getRequestURL().toString();
        return url.substring(0, url.indexOf(contextPath)) + contextPath;
    }

    private String cleanUrlFromScheme(String originalUrl) {

        if (originalUrl.startsWith("https://")) {
            originalUrl = originalUrl.substring(8);
        }
        if (originalUrl.startsWith("http://")) {
            originalUrl = originalUrl.substring(7);
        }
        return originalUrl;
    }


    /**
     * Gets base10 id representation from base62 of shortened url
     * and looking original URL in DB by this id.
     *
     * @param uniqueKeyForOriginalURL shortened url by our system
     * @return original URL
     */

    @Override
    @Transactional
    public String fromShortenUrlToOriginal(String uniqueKeyForOriginalURL) {
        Long urlId = EncodeUtil.fromBase62toBase10Encode(uniqueKeyForOriginalURL);
        String originalUrl = urlDataRepository.findById(urlId);
        if (originalUrl == null || originalUrl.length() == 0) {
            throw new NoDataFoundExceptionInDB(Constants.NO_DATA_FOUND_IN_DB);
        }
        return "http://" + originalUrl;
    }

}
