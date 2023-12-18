package org.example.utils;

import java.time.LocalDate;

public class DatePrefixingUtility extends AbstractDateTimePrefixingUtility<LocalDate> {
    public DatePrefixingUtility(String dateFormat) {
        super(dateFormat, LocalDate.class);
    }
}