package com.urlshortner.demo.urlshortnerdemo.repository;

import com.urlshortner.demo.urlshortnerdemo.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ShortUrlManageRepository  extends JpaRepository<ShortUrl,Long> {

    Optional<ShortUrl> findByShorturl(String username);

}
