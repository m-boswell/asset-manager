package org.example.utils;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;

public class LocalDateUtility extends AbstractDateTimeUtility<LocalDate> {
    public LocalDateUtility(String pattern) {
        super(pattern);
    }
    @Override
    public LocalDate parse(String s) {
        TemporalAccessor ta = getFormatter().parse(s);
        return LocalDate.from(ta);
    }
}
