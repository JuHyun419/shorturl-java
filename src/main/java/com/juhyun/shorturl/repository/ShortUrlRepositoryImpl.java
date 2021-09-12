package com.juhyun.shorturl.repository;

import com.juhyun.shorturl.entity.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

public class ShortUrlRepositoryImpl implements ShortUrlRepositoryCustom<ShortUrl, Long> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ShortUrl findMaxShortUrl() {
        Query query = new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id"));
        return mongoTemplate.findOne(query, ShortUrl.class);
    }
}
