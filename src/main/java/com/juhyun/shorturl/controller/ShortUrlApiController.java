package com.juhyun.shorturl.controller;

import com.juhyun.shorturl.controller.dto.ReadShortUrlResponse;
import com.juhyun.shorturl.controller.dto.ShortUrlRequest;
import com.juhyun.shorturl.controller.dto.ShortUrlResponse;
import com.juhyun.shorturl.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ShortUrlApiController {

    private final ShortUrlService shortUrlService;

    /**
     * URL 리디렉션 API
     * shortUrl -> originUrl
     * @param request
     * @return
     */
    @GetMapping("/short-url")
    public ResponseEntity<ReadShortUrlResponse> read(@RequestBody ShortUrlRequest request) {

        return null;
    }

    /**
     * URL 단축 API
     * originUrl -> shortUrl
     * @param request: 요청 url(origin + custom)
     * @return: shortUrl
     */
    @PostMapping("/short-url")
    public ResponseEntity<ReadShortUrlResponse> create(@RequestBody ShortUrlRequest request) {
        ReadShortUrlResponse response = shortUrlService.create(request);

        return ResponseEntity.ok().body(response);
    }
}