package com.urlshort.Url_shortService.Controller;

import com.urlshort.Url_shortService.model.*;
import com.urlshort.Url_shortService.repository.UrlRepository;
import com.urlshort.Url_shortService.service.UrlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


import java.io.IOException;

import java.time.LocalDateTime;



@RestController
public class UrlShortController
{
   // Url url= new Url();
    //url.setQtd()

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


    @GetMapping("/{shortlink}")
    public ResponseEntity<?> redirectToOriginalUrl (@PathVariable("shortlink") String shortLink,
                                                    HttpServletResponse response) throws IOException{

        if(StringUtils.isEmpty(shortLink))
        {
            UrlErrorResponse urlErrorResponse = new UrlErrorResponse();
            urlErrorResponse.setError("Url Invalida");
            urlErrorResponse.setStatus("400");
            return new ResponseEntity<UrlErrorResponse>(urlErrorResponse, HttpStatus.OK);
        }
        Url urlToRet = urlService.getEncodedUrl(shortLink);



        if(urlToRet == null)
        {
            UrlErrorResponse urlErrorResponse = new UrlErrorResponse();
            urlErrorResponse.setError("url não existe ou pode ter expirado");
            urlErrorResponse.setStatus("400");
            return new ResponseEntity<UrlErrorResponse>(urlErrorResponse, HttpStatus.OK);
        }
        if(urlToRet.getExpirationDate()!=null && urlToRet.getExpirationDate() .isBefore(LocalDateTime.now()))
        {
            UrlErrorResponse urlErrorResponse = new UrlErrorResponse();
            urlErrorResponse.setError("Url Expirada. Gere outra");
            urlErrorResponse.setStatus("200");
            return new ResponseEntity<UrlErrorResponse>(urlErrorResponse, HttpStatus.OK);
        }

        response.sendRedirect(urlToRet.getOriginalUrl());
        return null;
       // return ResponseEntity.status(HttpStatus.FOUND)
               // .location(URI.create("http://www.yahoo.com"))
               // .build();
    }

   @GetMapping ("/dashboard")
   public ResponseEntity<?> generateAmount(@RequestBody Qtd qtd)
   {
       Url urlToRet = urlService.generateAmount(qtd);

       if(urlToRet != null)
       {
           UrlResponse urlResponse = new UrlResponse();
           urlResponse.setQtd(urlToRet.getQtd());
           return new ResponseEntity<UrlResponse>(urlResponse, HttpStatus.OK);
       }

       UrlErrorResponse urlErrorResponse = new UrlErrorResponse();
       urlErrorResponse.setStatus("404");
       urlErrorResponse.setError("Houve um erro ao processar seu pedido. Por favor, tente novamente");
       return new ResponseEntity<UrlErrorResponse>(urlErrorResponse, HttpStatus.OK);
   }


}
