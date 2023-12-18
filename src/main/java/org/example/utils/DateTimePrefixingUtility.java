package org.example.utils;

import java.time.LocalDateTime;

public class DateTimePrefixingUtility extends AbstractDateTimePrefixingUtility<LocalDateTime> {
    public DateTimePrefixingUtility(String dateFormat) {
        super(dateFormat, LocalDateTime.class);
    }
    public DateTimePrefixingUtility(String dateFormat, int expectedFormatLength) {
        super(dateFormat, LocalDateTime.class, expectedFormatLength);
    }
}
