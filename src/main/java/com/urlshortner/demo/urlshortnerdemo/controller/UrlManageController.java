package com.urlshortner.demo.urlshortnerdemo.controller;

import com.urlshortner.demo.urlshortnerdemo.entity.ShortUrl;
import com.urlshortner.demo.urlshortnerdemo.repository.ShortUrlRedisRepository;
import com.urlshortner.demo.urlshortnerdemo.services.manageurlservice.ManageUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.urlshortner.demo.urlshortnerdemo.contant.Constant.API_MAPPER;

@RestController
@RequestMapping(path = API_MAPPER)
public class UrlManageController {

    @Autowired
    ManageUrlService manageUrlService;

    @Autowired
    ShortUrlRedisRepository shortUrlRedisRepository;

    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> test() {

        Map<String, Object> response = new HashMap<>();
        response.put("api_message", "API is working !");

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @PostMapping(path = "/get_original_url")
    public ResponseEntity<Map<String, Object>> getOrignalRequest(
            @RequestParam String url) {

        Map<String, Object> response = new HashMap<>();

        try{

            ShortUrl shorturl =null;
            try{
               shorturl= shortUrlRedisRepository.getOrignalUrl(url);
                if (shorturl != null) {

                    System.out.println("get from cache");

                }else {
                    //maintain cache
                    shortUrlRedisRepository.createUrl(shorturl);
                }
            }catch (Exception e){
                shorturl = manageUrlService.getOrignalUrl(url);
            }

            response.put("data", shorturl);
            response.put("api_status", true);
            response.put("api_message", "found url");

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            System.out.println("finding url cause : " + e.getMessage());
            response.put("api_status", false);
            response.put("api_message", "error cause : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }


    @PostMapping(path = "/user/createshort_url")
    public @ResponseBody ResponseEntity<Map<String, Object>> createShortUrl(
            @RequestParam String orignalurl) {

        Map<String, Object> response = new HashMap<>();

        try {

            ShortUrl shorturls = manageUrlService.createUrl(orignalurl);

            //maintain cache
            shortUrlRedisRepository.createUrl(shorturls);

            response.put("data", shorturls);
            response.put("api_status", true);
            response.put("api_message", "short url genrated");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            System.out.println("register user cause : " + e.getMessage());
            response.put("api_status", false);
            response.put("api_message", "error cause : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping(path = "/admin/get_all_url")
    public @ResponseBody ResponseEntity<Map<String, Object>> getAllUrl() {

        Map<String, Object> response = new HashMap<>();

        try {

            if (shortUrlRedisRepository.redis_connected()) {
                //maintain cache
                List<ShortUrl> cache = shortUrlRedisRepository.getAllUrl();
                if (!cache.isEmpty()) {
                    System.out.println("getting from cache");
                }
                response.put("data", cache);
            } else {

                response.put("data", manageUrlService.getAllUrl());
            }
            response.put("api_status", true);
            response.put("api_message", "all saved url fetched");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("fetch all user error cause : " + e.getMessage());
            response.put("api_status", false);
            response.put("api_message", "error cause : " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

}
