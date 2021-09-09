package com.juhyun.shorturl.controller;

import com.juhyun.shorturl.controller.dto.ShortUrlRequest;
import com.juhyun.shorturl.controller.dto.ShortUrlResponse;
import com.juhyun.shorturl.service.ShortUrlService;
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
@RequestMapping("/api")
public class ShortUrlApiController {

    private final ShortUrlService shortUrlService;

    public ShortUrlApiController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping("/short-url")
    public List<ShortUrlResponse> findAll() {
        log.info("findAll");
        return shortUrlService.findAll();
    }

    @PostMapping("/short-url")
    public ResponseEntity<ShortUrlResponse> create(@RequestBody ShortUrlRequest request) {
        ShortUrlResponse shortUrlResponse = shortUrlService.create(request);

        log.info("controller: " + shortUrlResponse);

        return ResponseEntity.ok().body(null);
    }
}