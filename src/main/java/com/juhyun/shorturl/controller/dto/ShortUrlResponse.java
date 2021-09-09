package com.juhyun.shorturl.controller.dto;

import com.juhyun.shorturl.entity.ShortUrl;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ShortUrlResponse {

    private String id;

    private String shortUrl;

    private String originUrl;

    private String customUrl;

    private Integer requestCount;

    public static ShortUrl dtoToEntity(ShortUrlResponse dto) {
        return ShortUrl.builder()
                .id(dto.id)
                .shortUrl(dto.shortUrl)
                .originUrl(dto.originUrl)
                .customUrl(dto.customUrl)
                .requestCount(dto.requestCount)
                .build();
    }
}