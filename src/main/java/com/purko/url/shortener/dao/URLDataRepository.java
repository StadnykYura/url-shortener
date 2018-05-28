package com.purko.url.shortener.dao;

import com.purko.url.shortener.model.UrlData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * interface is used as DAO layer to get data from DB
 */
public interface URLDataRepository extends JpaRepository<UrlData, Long>{

    @Query(value = "select u from UrlData u where u.originalUrl = :originalUrl")
    Optional<UrlData> findExistingOriginalUrl(@Param(value = "originalUrl") String originalClientUrl);

    @Query(value = "select u.originalUrl from UrlData u where u.id = :id")
    String findById(@Param(value = "id") Long urlId);

}
