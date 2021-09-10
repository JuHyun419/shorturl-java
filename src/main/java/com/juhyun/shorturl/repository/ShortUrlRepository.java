package com.juhyun.shorturl.repository;

import com.juhyun.shorturl.entity.ShortUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository extends MongoRepository<ShortUrl, String>, ShortUrlRepositoryCustom {

    //@Query("{originUrl :?originUrl}")
    ShortUrl findByLongUrl(String longUrl);

}
