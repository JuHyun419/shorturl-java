package com.juhyun.shorturl.repository;

import com.juhyun.shorturl.entity.ShortUrl;

public interface ShortUrlRepositoryCustom {

    ShortUrl findMaxShortUrl();

}
