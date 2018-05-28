package com.purko.url.shortener.model;

import com.purko.url.shortener.util.Constants;

import javax.persistence.*;
import java.util.Date;

/**
 * Class to represent URL in DB.
 * 'Id' field is used to encode it to base62 key of particular url
 * 'updatedTime' represents a time when this url was asked to be shortened.
 *  It can be used to delete old rows from DB if it weren't asked for a particular time with some Job (e.g. quartz);
 *
 * @author Yura
 * @version 1.0
 */
@Entity
@Table(name="URL_DATA")
public class UrlData {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    @Column(name="id")
    private Long id;

    @Column(name = "url")
    private String originalUrl;

    @Column(name = "updated_time")
    private Date updatedTime;

    public UrlData() {
    }

    public UrlData(Long id, String originalUrl, Date updatedTime) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.updatedTime = updatedTime;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "UrlData{" +
                "id=" + id +
                ", originalUrl='" + originalUrl + '\'' +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
