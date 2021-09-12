package com.juhyun.shorturl.repository;

import com.juhyun.shorturl.entity.ShortUrl;
import com.juhyun.shorturl.util.TinyUrl;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class ShortUrlRepositoryTest {

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Disabled
    @Test
    void findTest() {
        // given
        ShortUrl shortUrl = makeEntity();
        shortUrlRepository.save(shortUrl);

        // when
        ShortUrl newEntity = shortUrlRepository.findByLongUrl(shortUrl.getLongUrl()).get();

        // then
        assertThat(shortUrl).isEqualTo(newEntity);
        assertThat(shortUrl.getShortUrl()).isEqualTo(newEntity.getShortUrl());
        assertThat(shortUrl.getLongUrl()).isEqualTo(newEntity.getLongUrl());
    }

    @Disabled
    @Test
    void insertDummyData() {
        IntStream.range(0, 10000).forEach(i -> {
            shortUrlRepository.save(makeEntity());
        });
    }

    private ShortUrl makeEntity() {
        Long count = shortUrlRepository.countAllDocuments();
        long id = (count > 0)
                ? shortUrlRepository.findMaxShortUrl().get_id() + 1
                : 1;
        String shortUrl = TinyUrl.idToShortUrl(id);
        String longUrl = "http://localhost:8080/" + RandomStringUtils.randomAlphanumeric(30);

        return ShortUrl.builder()
                ._id(id)
                .shortUrl(shortUrl)
                .longUrl(longUrl)
                .customUrl("")
                .requestCount(1)
                .build();
    }

}