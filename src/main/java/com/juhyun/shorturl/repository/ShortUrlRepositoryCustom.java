package com.juhyun.shorturl.repository;

public interface ShortUrlRepositoryCustom<T, ID> {

    T findMaxShortUrl();

}
