package com.juhyun.shorturl.repository;

import com.juhyun.shorturl.entity.ShortUrl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class ShortUrlRepositoryTest {

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Test
    void test() {
        // given
        String existLongUrl = "http://naver.comasd@asdzcxzcxzc$%vcxxzcvxcv";

        // when
        ShortUrl newEntity = shortUrlRepository.findByLongUrl(existLongUrl).get();

        // then
        assertThat(existLongUrl).isEqualTo(newEntity.getLongUrl());
    }

}