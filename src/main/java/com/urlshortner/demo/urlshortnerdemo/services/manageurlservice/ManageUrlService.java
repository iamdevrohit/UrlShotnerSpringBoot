package com.urlshortner.demo.urlshortnerdemo.services.manageurlservice;

import com.urlshortner.demo.urlshortnerdemo.entity.ShortUrl;
import com.urlshortner.demo.urlshortnerdemo.entity.User;
import com.urlshortner.demo.urlshortnerdemo.repository.ShortUrlManageRepository;
import com.urlshortner.demo.urlshortnerdemo.repository.UserManageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ManageUrlService implements ManageUrl{

    @Autowired
    UserManageRepository userManageRepository;

    @Autowired
    ShortUrlManageRepository shortUrlManageRepository;


    @Override
    public ShortUrl createUrl(String originalurl) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userManageRepository.findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("illegal user found as  "+username));

        String short_key =""+ Calendar.getInstance().getTimeInMillis();

        ShortUrl shorturls = new ShortUrl();

        shorturls.setAutherid(user.get().getId());
        shorturls.setOrignalurl(originalurl);
        shorturls.setShortkey(short_key);
        shorturls.setShorturl("www.short_url/"+short_key);

        shortUrlManageRepository.save(shorturls);

        return shorturls;
    }



    @Override
    public ShortUrl getOrignalUrl(String shorturl) {

        Optional<ShortUrl> url = shortUrlManageRepository.findByShorturl(shorturl);

        url.orElseThrow(() -> new UsernameNotFoundException("original link not found"));

        return url.get();
    }


    @Override
    public List<ShortUrl> getAllUrl() {

        return shortUrlManageRepository.findAll();
    }
}
