package com.juhyun.shorturl.service;

import com.juhyun.shorturl.controller.dto.ShortUrlRequest;
import com.juhyun.shorturl.controller.dto.ShortUrlResponse;
import com.juhyun.shorturl.entity.ShortUrl;
import com.juhyun.shorturl.repository.ShortUrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;

    public ShortUrlService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    @Transactional(readOnly = true)
    public List<ShortUrlResponse> findAll() {
        List<ShortUrl> shortUrlList = shortUrlRepository.findAll();

        return shortUrlList.stream()
                .map(ShortUrl::entityToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ShortUrlResponse create(ShortUrlRequest request) {
        Assert.notNull(request, "ShortUrlRequest must not be null");

        ShortUrl originUrl = shortUrlRepository.findByOriginUrl(request.getOriginUrl());

        // 기존에 존재하는 경우
        if (existOriginUrl(originUrl)) {
            log.info("exist");
            originUrl.increaseRequestCount();
            shortUrlRepository.save(originUrl);
            return ShortUrl.entityToDto(originUrl);
        }

        // 2) 존재하지 않으면 -> 등록 후 shortUrl 만든 후 리턴



        return null;
    }

    private boolean existOriginUrl(ShortUrl originUrl) {
        return originUrl != null;
    }
}