package com.urlshortner.demo.urlshortnerdemo.entity.cache;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@RedisHash("shorturlcache")
@Data
public class ShortUrlCache implements Serializable {

    private Long id;
    private Long autherid;
    String shortkey;
    String shorturl;
    String orignalurl;
    LocalDateTime created_on;
}
