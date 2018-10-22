package com.travelstory.dto.statistic;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

//@Converter(autoApply = true)
@Slf4j
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        log.info("from locdatetime {}", localDateTime);
        return Optional.ofNullable(localDateTime).map(Timestamp::valueOf).map(a -> {
            log.info(">>> {}", a);
            return a;
        }).orElse(null);

    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        log.info("to locdatetime {}", timestamp);
        return Optional.ofNullable(timestamp).map(Timestamp::toLocalDateTime).orElse(null);

    }

}