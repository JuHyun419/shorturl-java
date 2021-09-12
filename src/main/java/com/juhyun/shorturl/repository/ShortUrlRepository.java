package com.juhyun.shorturl.repository;

import com.juhyun.shorturl.entity.ShortUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortUrlRepository extends MongoRepository<ShortUrl, Long>, ShortUrlRepositoryCustom<ShortUrl, Long> {

    //@Query("{originUrl :?originUrl}")
    Optional<ShortUrl> findByLongUrl(String longUrl);

    @Query(value = "{}", count = true)
    Long countAllDocuments();

}
