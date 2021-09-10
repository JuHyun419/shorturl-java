package com.juhyun.shorturl.entity.sequence;

import com.juhyun.shorturl.entity.ShortUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ShortUrlListener extends AbstractMongoEventListener<ShortUrl> {

    private final SequenceGeneratorService generatorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<ShortUrl> event) {
        event.getSource().setId(generatorService.generateSequence(ShortUrl.SEQUENCE_NAME));
    }
}
