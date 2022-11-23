package com.urlshortner.demo.urlshortnerdemo.repository;

import com.urlshortner.demo.urlshortnerdemo.entity.ShortUrl;
import com.urlshortner.demo.urlshortnerdemo.entity.cache.ShortUrlCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.exceptions.JedisConnectionException;
import java.util.ArrayList;
import java.util.List;
import static com.urlshortner.demo.urlshortnerdemo.contant.Constant.REDIS_HOST;
import static com.urlshortner.demo.urlshortnerdemo.contant.Constant.REDIS_PORT;

@Repository
public class ShortUrlRedisRepository {

    static String HASH_KEY = "shorturl";

    @Autowired
    RedisTemplate redisTemplate;


    public boolean redis_connected() {
        try (Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT)) {
            new String((byte[]) jedis.sendCommand(Protocol.Command.INFO));
            System.out.println("REDIS CONNECTED");
            return true;
        } catch (Exception e) {
            System.out.println("REDIS NOT CONNECTED");
            return false;
        }
    }


    public ShortUrl createUrl(ShortUrl shorturls) {

        ShortUrlCache redis_short_url = new ShortUrlCache();

        redis_short_url.setShorturl(shorturls.getShorturl());
        redis_short_url.setAutherid(shorturls.getAutherid());
        redis_short_url.setOrignalurl(shorturls.getOrignalurl());
        redis_short_url.setShortkey(shorturls.getShortkey());
        redis_short_url.setId(shorturls.getId());

        redisTemplate.opsForHash().put(HASH_KEY, shorturls.getShorturl(), redis_short_url);

        return shorturls;
    }


    public ShortUrl getOrignalUrl(String shorturl) {
        ShortUrlCache shortUrlCache = (ShortUrlCache) redisTemplate.opsForHash().get(HASH_KEY, shorturl);

        ShortUrl shorturls = null;
        if (shortUrlCache != null) {
            shorturls = new ShortUrl();
            shorturls.setShorturl(shortUrlCache.getShorturl());
            shorturls.setOrignalurl(shortUrlCache.getOrignalurl());
            shorturls.setAutherid(shortUrlCache.getAutherid());
            shorturls.setId(shortUrlCache.getId());
            shorturls.setCreated_on(shortUrlCache.getCreated_on());
            shorturls.setShortkey(shortUrlCache.getShortkey());
        }
        return shorturls;
    }


    public List<ShortUrl> getAllUrl() throws JedisConnectionException {

        List<ShortUrl> shorturls = new ArrayList<>();

        redisTemplate.opsForHash().values(HASH_KEY)
                .stream().forEach(value -> {

           ShortUrlCache cache = (ShortUrlCache) value;

           shorturls.add(new ShortUrl(
                   cache.getId(),
                   cache.getAutherid(),
                   cache.getShortkey(),
                   cache.getShorturl(),
                   cache.getOrignalurl(),
                   cache.getCreated_on()
           ));

        });

        return shorturls;
    }


}
