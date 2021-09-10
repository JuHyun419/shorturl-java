package com.juhyun.shorturl.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TinyUrlTest {

    @Test
    void id로_만든_url은_동일하다() {
        // given
        long id = 42812;

        // when
        String url = TinyUrl.makeShortUrl(id);
        long newId = TinyUrl.makeId(url);

        // then
        assertThat(id).isEqualTo(newId);
    }

    @Test
    void url로_만든_id는_동일하다() {
        // given
        String url = "M7Cx9K";

        // when
        long id = TinyUrl.makeId(url);
        String newUrl = TinyUrl.makeShortUrl(id);

        // then
        assertThat(url).isEqualTo(newUrl);
    }

}