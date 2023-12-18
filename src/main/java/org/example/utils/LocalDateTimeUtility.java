package org.example.utils;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;

public class LocalDateTimeUtility extends AbstractDateTimeUtility<LocalDateTime> {
    public LocalDateTimeUtility(String pattern) {
        super(pattern);
    }
    @Override
    public LocalDateTime parse(String s) {
        TemporalAccessor ta = getFormatter().parse(s);
        return LocalDateTime.from(ta);
    }
}
