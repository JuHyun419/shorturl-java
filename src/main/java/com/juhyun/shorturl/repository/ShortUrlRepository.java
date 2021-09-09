package com.juhyun.shorturl.repository;

import com.juhyun.shorturl.entity.ShortUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShortUrlRepository extends MongoRepository<ShortUrl, String> {

    ShortUrl findByOriginUrl(String originUrl);

}
