package com.juhyun.shorturl.service;

import com.juhyun.shorturl.controller.dto.ReadShortUrlResponse;
import com.juhyun.shorturl.controller.dto.ShortUrlRequest;
import com.juhyun.shorturl.controller.dto.ShortUrlResponse;
import com.juhyun.shorturl.entity.ShortUrl;
import com.juhyun.shorturl.repository.ShortUrlRepository;
import com.juhyun.shorturl.util.TinyUrl;
import com.juhyun.shorturl.util.UrlValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;

    @Transactional(readOnly = true)
    public ShortUrlResponse find(String longUrl) {
        Assert.notNull(longUrl, "longUrl must not be null");

        ShortUrl shortUrl = shortUrlRepository.findByLongUrl(longUrl)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 longUrl 입니다: " + longUrl));

        return ShortUrl.entityToResponseDto(shortUrl);
    }

    @Transactional
    public ReadShortUrlResponse create(ShortUrlRequest request) {
        Assert.notNull(request, "ShortUrlRequest must not be null");
        UrlValidation.checkUrl(request.getLongUrl());

        /* 클라이언트가 요청한 longUrl이 DB에 존재하는 경우 */
        ShortUrl originUrl = shortUrlRepository.findByLongUrl(request.getLongUrl()).orElse(null);
        if (existOriginUrl(originUrl)) {
            return ShortUrl.entityToReadDto(originUrl);
        }

        /*
          클라이언트가 요청한 longUrl이 DB에 존재하지 않는 경우
          1) id 생성(DB에 존재하는 max + 1)
          2) shortUrl 생성
          3) ShortUrl Entity 생성
          4) 저장 후 리턴
         */
        Long count = shortUrlRepository.countAllDocuments();
        long maxId = (count > 0)
                ? shortUrlRepository.findMaxShortUrl().get_id() + 1
                : 1;
        log.info("count: " + count + ", maxId: " + maxId);

        String shortUrl = TinyUrl.idToShortUrl(maxId);
        ShortUrl entity = ShortUrl.builder()
                .shortUrl(shortUrl)
                .longUrl(request.getLongUrl())
                .customUrl(request.getCustomUrl())
                .requestCount(1)
                .build();

        ShortUrl newEntity = shortUrlRepository.save(entity);

        return ShortUrl.entityToReadDto(newEntity);
    }

    private boolean existOriginUrl(ShortUrl originUrl) {
        return originUrl != null;
    }
}