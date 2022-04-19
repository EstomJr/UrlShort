package com.urlshort.Url_shortService.service;

import com.urlshort.Url_shortService.model.LinklDto;
import com.urlshort.Url_shortService.model.Qtd;
import com.urlshort.Url_shortService.model.Url;
import org.springframework.stereotype.Service;

@Service
public interface UrlService {

    public Url generateShortUrl(LinklDto linkDto);
    public Url persistShortLink(Url url);
    public Url getEncodedUrl(String url);
    public void deleteShortLink(Url url);

    public Url generateAmount(Qtd qtd);
}
