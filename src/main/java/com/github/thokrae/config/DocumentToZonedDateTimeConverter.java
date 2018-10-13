package com.github.thokrae.config;

import static com.github.thokrae.config.ZonedDateTimeToDocumentConverter.DATE_TIME;
import static com.github.thokrae.config.ZonedDateTimeToDocumentConverter.ZONE;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@ReadingConverter
public class DocumentToZonedDateTimeConverter implements Converter<Document, ZonedDateTime> {

    @Override
    public ZonedDateTime convert(@Nullable Document document) {
        if (document == null) return null;

        Date dateTime = document.getDate(DATE_TIME);
        String zoneId = document.getString(ZONE);
        ZoneId zone = ZoneId.of(zoneId);

        return ZonedDateTime.ofInstant(dateTime.toInstant(), zone);
    }
}
