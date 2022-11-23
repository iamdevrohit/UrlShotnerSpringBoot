package com.urlshortner.demo.urlshortnerdemo.services.manageurlservice;

import com.urlshortner.demo.urlshortnerdemo.entity.ShortUrl;
import java.util.List;

public interface ManageUrl {

    ShortUrl createUrl(String originalurl);

    ShortUrl getOrignalUrl(String shorturl);

    List<ShortUrl> getAllUrl();
}
