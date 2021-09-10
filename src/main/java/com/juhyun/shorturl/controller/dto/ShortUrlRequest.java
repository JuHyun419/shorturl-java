package com.juhyun.shorturl.controller.dto;

import com.juhyun.shorturl.entity.ShortUrl;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ShortUrlRequest {

    @NotBlank(message = "longUrl must not be blank")
    private String longUrl;

    private String customUrl;

}