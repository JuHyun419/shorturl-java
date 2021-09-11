package com.juhyun.shorturl.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrlRequest {

    @NotBlank(message = "longUrl must not be blank")
    private String longUrl;

    private String customUrl;

}