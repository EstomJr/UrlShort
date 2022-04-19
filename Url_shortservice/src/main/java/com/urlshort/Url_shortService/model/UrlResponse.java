package com.urlshort.Url_shortService.model;

import java.time.LocalDateTime;

public class UrlResponse
{
    private String originalUrl;
    private String shortLnk;
    private LocalDateTime expirationDate;
    private int qtd;

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public UrlResponse(String originalUrl, String shortLnk, LocalDateTime expirationDate) {
        this.originalUrl = originalUrl;
        this.shortLnk = shortLnk;
        this.expirationDate = expirationDate;
        this.qtd = qtd;
    }

    public UrlResponse() {
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortLnk() {
        return shortLnk;
    }

    public void setShortLnk(String shortLnk) {
        this.shortLnk = shortLnk;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "UrlResponse{" +
                "originalUrl='" + originalUrl + '\'' +
                ", shortLnk='" + shortLnk + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
