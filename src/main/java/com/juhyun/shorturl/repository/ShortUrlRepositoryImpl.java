package com.juhyun.shorturl.repository;

import com.juhyun.shorturl.entity.ShortUrl;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

public class ShortUrlRepositoryImpl implements ShortUrlRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public ShortUrlRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ShortUrl findMaxShortUrl() {
        Query query = new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id"));
        return mongoTemplate.findOne(query, ShortUrl.class);
    }
}
