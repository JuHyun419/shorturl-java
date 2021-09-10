package com.juhyun.shorturl.service;

import com.juhyun.shorturl.controller.dto.ReadShortUrlResponse;
import com.juhyun.shorturl.controller.dto.ShortUrlRequest;
import com.juhyun.shorturl.entity.ShortUrl;
import com.juhyun.shorturl.repository.ShortUrlRepository;
import com.juhyun.shorturl.util.TinyUrl;
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

    @Transactional
    public ReadShortUrlResponse create(ShortUrlRequest request) {
        Assert.notNull(request, "ShortUrlRequest must not be null");

        // TODO: url 유효성 검사

        /* 클라이언트가 요청한 longUrl이 DB에 존재하는 경우 */
        ShortUrl originUrl = shortUrlRepository.findByLongUrl(request.getLongUrl());
        if (existOriginUrl(originUrl)) {
            return ShortUrl.entityToDto(originUrl);
        }

        /*
          클라이언트가 요청한 longUrl이 DB에 존재하지 않는 경우
          1) id 생성
          2) shortUrl 생성
          3) ShortUrl Entity 생성
          4) 저장 후 리턴
         */
        long maxId = shortUrlRepository.findMaxShortUrl().get_id() + 1;
        log.info("Service MaxId: " + maxId);

        String shortUrl = TinyUrl.idToShortUrl(maxId);
        ShortUrl entity = ShortUrl.builder()
                .shortUrl(shortUrl)
                .longUrl(request.getLongUrl())
                .customUrl(request.getCustomUrl())
                .requestCount(1)
                .build();

        ShortUrl newEntity = shortUrlRepository.save(entity);

        return ShortUrl.entityToDto(newEntity);
    }

    private boolean existOriginUrl(ShortUrl originUrl) {
        return originUrl != null;
    }
}