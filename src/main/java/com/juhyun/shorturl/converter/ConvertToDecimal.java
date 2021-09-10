package com.juhyun.shorturl.converter;

import org.bson.types.Decimal128;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.CustomConversions;

import java.math.BigDecimal;
import java.util.Arrays;

public class ConvertToDecimal {

    @Bean
    CustomConversions customConverions() {
        Converter<Decimal128, BigDecimal> decimal128ToBigDecimal = s ->
                s == null ? null : s.bigDecimalValue();

        Converter<BigDecimal, Decimal128> bigDecimalToDecimal128 = s ->
                s == null ? null : new Decimal128(s);

        return new CustomConversions(Arrays.asList(decimal128ToBigDecimal, bigDecimalToDecimal128));
    }

}
