package com.juhyun.shorturl.service;

import com.juhyun.shorturl.controller.dto.ShortUrlResponse;
import com.juhyun.shorturl.entity.ShortUrl;
import com.juhyun.shorturl.repository.ShortUrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

class ShortUrlServiceTest {

    @Mock
    private ShortUrlRepository shortUrlRepository;

    @InjectMocks
    private ShortUrlService shortUrlService;

    @Test
    void findTest() {
        // given
        final String longUrl = "http://naver.comasd@asdzcxzcxzc$%vcxxzcvxcv";
        ShortUrl shortUrl = ShortUrl.builder()
                .shortUrl("mKx8")
                .longUrl(longUrl)
                .customUrl("")
                .requestCount(1)
                .build();

        // stubbing
        given(shortUrlRepository.findByLongUrl(longUrl)).willReturn(Optional.ofNullable(shortUrl));

        // when
        ShortUrl entity = ShortUrlResponse.dtoToEntity(shortUrlService.find(longUrl));

        // then
        assertThat(longUrl).isEqualTo(entity.getLongUrl());
        verify(shortUrlRepository).findByLongUrl(anyString());
    }


}