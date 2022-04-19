package com.urlshort.Url_shortService.service;


import com.google.common.hash.Hashing;
import com.urlshort.Url_shortService.model.LinklDto;
import com.urlshort.Url_shortService.model.Qtd;
import com.urlshort.Url_shortService.model.Url;
import com.urlshort.Url_shortService.repository.UrlRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlRepository urlRepository;


    @Override
    public Url generateShortUrl(LinklDto linkDto) {
        if(StringUtils.isNotEmpty(linkDto.getUrl()))
        {
            String encodedUrl = encodeUrl(linkDto.getUrl());
            Url urlToPersist = new Url();
            urlToPersist.setCreationDate(LocalDateTime.now());
            urlToPersist.setOriginalUrl(linkDto.getUrl());
            urlToPersist.setShortLink(encodedUrl);
            Url urlToRet = persistShortLink(urlToPersist);

            if(urlToRet != null)
                return urlToRet;

            return null;
        }
        return null;
    }

    private String encodeUrl(String url)
    {
        String encodedUrl = "";
        LocalDateTime time = LocalDateTime.now();
        encodedUrl = Hashing.murmur3_32_fixed()
                .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
        return encodedUrl;
    }

    @Override
    public Url persistShortLink(Url url) {
        Url urlToRet = urlRepository.save(url);
        return urlToRet;
    }

    @Override
    public Url getEncodedUrl(String url) {
        Url urlToRet = urlRepository.findByShortLink(url);
        urlToRet.setQtd(urlToRet.getQtd() != 0 ? urlToRet.getQtd() + 1 : 1 );
        urlRepository.save(urlToRet);
        return urlToRet;
    }

    @Override
    public void deleteShortLink(Url url) {

        urlRepository.delete(url);

    }

    @Override
    public Url generateAmount(Qtd qtd) {
        return null;
    }
}
