package com.urlshort.Url_shortService.Controller;

import com.urlshort.Url_shortService.model.LinklDto;
import com.urlshort.Url_shortService.model.Url;
import com.urlshort.Url_shortService.model.UrlErrorResponse;
import com.urlshort.Url_shortService.model.UrlResponse;
import com.urlshort.Url_shortService.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortController
{
    @Autowired
    private UrlService urlService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateShortLink(@RequestBody LinklDto linklDto)
    {
        Url urlToRet = urlService.generateShortUrl(linklDto);

        if(urlToRet != null)
        {
            UrlResponse urlResponse = new UrlResponse();
            urlResponse.setOriginalUrl(urlToRet.getOriginalUrl());
            urlResponse.setExpirationDate(urlToRet.getExpirationDate());
            urlResponse.setShortLnk(urlToRet.getShortLink());
            return new ResponseEntity<UrlResponse>(urlResponse, HttpStatus.OK);
        }

        UrlErrorResponse urlErrorResponse = new UrlErrorResponse();
        urlErrorResponse.setStatus("404");
        urlErrorResponse.setError("Houve um erro ao processar seu pedido. Por favor, tente novamente");
        return new ResponseEntity<UrlErrorResponse>(urlErrorResponse, HttpStatus.OK);
    }
}
