package com.juhyun.shorturl.util;

import org.junit.jupiter.api.Test;

import static com.juhyun.shorturl.util.UrlValidation.isValidUrl;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UrlValidationTest {

    @Test
    void localhost_url도_통과한다() {
        // given
        String localUrl1 = "http://localhost:8080";
        String localUrl2 = "https://localhost:8080";
        String localUrl3 = "http://localhost:3000";
        String localUrl4 = "https://localhost:3000";

        // when & then
        assertThat(isValidUrl(localUrl1)).isEqualTo(true);
        assertThat(isValidUrl(localUrl2)).isEqualTo(true);
        assertThat(isValidUrl(localUrl3)).isEqualTo(true);
        assertThat(isValidUrl(localUrl4)).isEqualTo(true);
    }

    @Test
    void 일반_url도_통과한다() {
        // given
        String successUrl1 = "http://foo.bar.com";
        String successUrl2 = "https://foo.bar.com";
        String successUrl3 = "http://juhyun.com/wiki/$3asdasd-is";

        // when & then
        assertThat(isValidUrl(successUrl1)).isEqualTo(true);
        assertThat(isValidUrl(successUrl2)).isEqualTo(true);
        assertThat(isValidUrl(successUrl3)).isEqualTo(true);
    }

    @Test
    void 특정_스키마를_설정한_url은_통과한다() {
        // given
        String[] schemes = {"ftp", "ldap", "juhyun"};
        String url1 = "ldap://foo.bar.com";
        String url2 = "juhyun://foo.bar.com";

        // when & then
        assertThat(isValidUrl(schemes, url1)).isEqualTo(true);
        assertThat(isValidUrl(schemes, url2)).isEqualTo(true);
    }

    @Test
    void 유효하지않은_url은_실패한다() {
        // given
        String failUrl1 = "foo.bar.com";
        String failUrl2 = "ldap://foo.bar.com:8080";
        String failUrl3 = "telnet://192.168.100.2:80";

        // when & then
        assertThat(isValidUrl(failUrl1)).isEqualTo(false);
        assertThat(isValidUrl(failUrl2)).isEqualTo(false);
        assertThat(isValidUrl(failUrl3)).isEqualTo(false);
    }

}