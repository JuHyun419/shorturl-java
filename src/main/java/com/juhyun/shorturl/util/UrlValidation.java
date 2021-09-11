package com.juhyun.shorturl.util;

import org.apache.commons.validator.routines.UrlValidator;

public class UrlValidation {

    private UrlValidation () { }

    public static boolean isValidUrl(String url) {
        return isValidUrl(null, url);
    }

    public static boolean isValidUrl(String[] schemes, String url) {
        return new UrlValidator(schemes, UrlValidator.ALLOW_LOCAL_URLS).isValid(url);
    }

}
