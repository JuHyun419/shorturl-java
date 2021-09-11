package com.juhyun.shorturl.util;

import org.apache.commons.validator.routines.UrlValidator;

public class UrlValidation {

    private UrlValidation () { }

    public static void checkUrl(String url) {
        checkUrl(null, url);
    }

    public static void checkUrl(String[] schemes, String url) {
        if (isValidUrl(schemes, url)) {
            throw new RuntimeException("요청하신 url(" + url + ")이 올바르지 않습니다.");
        }
    }

    public static boolean isValidUrl(String url) {
        return isValidUrl(null, url);
    }

    public static boolean isValidUrl(String[] schemes, String url) {
        return new UrlValidator(schemes, UrlValidator.ALLOW_LOCAL_URLS).isValid(url);
    }

}
