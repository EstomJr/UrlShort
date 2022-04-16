package com.urlshort.Url_shortService.model;

public class LinklDto
{
    private String url;

    public LinklDto(String url) {
        this.url = url;
    }

    public LinklDto() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "LinklDto{" +
                "url='" + url + '\'' +
                '}';
    }
}
