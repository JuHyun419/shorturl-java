package com.juhyun.shorturl.controller;

import com.juhyun.shorturl.controller.dto.ReadShortUrlResponse;
import com.juhyun.shorturl.controller.dto.ShortUrlRequest;
import com.juhyun.shorturl.controller.dto.ShortUrlResponse;
import com.juhyun.shorturl.service.ShortUrlService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/api")
public class ShortUrlApiController {

    private ShortUrlService shortUrlService;

    /**
     * URL 리디렉션 API
     * shortUrl -> originUrl
     */
    @GetMapping("/{short-url}")
    public ResponseEntity<ShortUrlResponse> read(@PathVariable("short-url") String request) {
        ShortUrlResponse response = shortUrlService.find(request);
        return ResponseEntity.ok().body(response);
    }

    /**
     * URL 단축 API
     * originUrl -> shortUrl
     * @param request: 요청 url(origin + custom)
     * @return: shortUrl
     */
    @PostMapping("/short-url")
    public ResponseEntity<ReadShortUrlResponse> create(@Valid @RequestBody ShortUrlRequest request) {
        ReadShortUrlResponse response = shortUrlService.create(request);

        return ResponseEntity.ok().body(response);
    }
}