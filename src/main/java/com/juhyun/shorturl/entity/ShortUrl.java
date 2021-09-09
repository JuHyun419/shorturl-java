package com.juhyun.shorturl.entity;

import com.juhyun.shorturl.controller.dto.ShortUrlResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Document(collection = "shorturl")
public class ShortUrl {
팅
    @Id
    private String id;

    @NotBlank(message = "shortUrl must not be blank")
    private String shortUrl;

    private String originUrl;

    private String customUrl;

    private Integer requestCount;

    public void increaseRequestCount() {
        this.requestCount++;
    }

    public static ShortUrlResponse entityToDto(ShortUrl entity) {
        return ShortUrlResponse.builder()
                .id(entity.id)
                .shortUrl(entity.shortUrl)
                .originUrl(entity.originUrl)
                .customUrl(entity.customUrl)
                .requestCount(entity.requestCount)
                .build();
    }
}