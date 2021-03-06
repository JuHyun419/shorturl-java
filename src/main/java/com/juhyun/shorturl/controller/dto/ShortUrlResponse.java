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

    private Long _id;

    private String shortUrl;

    private String longUrl;

    private String customUrl;

    private Integer requestCount;

    public static ShortUrl dtoToEntity(ShortUrlResponse dto) {
        return ShortUrl.builder()
                ._id(dto._id)
                .shortUrl(dto.shortUrl)
                .longUrl(dto.longUrl)
                .customUrl(dto.customUrl)
                .requestCount(dto.requestCount)
                .build();
    }
}