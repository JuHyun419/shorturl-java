package com.juhyun.shorturl.entity;

import com.juhyun.shorturl.controller.dto.ReadShortUrlResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Document(collection = "shorturl")
public class ShortUrl {

    @Transient
    public static final String SEQUENCE_NAME = "shorturl_sequence";

    @Id
    private Long _id;

    @NotBlank(message = "shortUrl must not be blank")
    private String shortUrl;

    @NotBlank(message = "longUrl must not be blank")
    private String longUrl;

    private String customUrl;

    private Integer requestCount;

    public void setId(Long _id) {
        this._id = _id;
    }

    public void increaseRequestCount() {
        this.requestCount++;
    }

    public static ReadShortUrlResponse entityToDto(ShortUrl entity) {
        return ReadShortUrlResponse.builder()
                .shortUrl(entity.getShortUrl())
                .build();
    }
}