package com.purko.url.shortener.model;

import com.purko.url.shortener.util.Constants;

import javax.persistence.*;
import java.util.Date;

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
