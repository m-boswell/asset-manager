package org.example.utils;

import java.time.temporal.TemporalAccessor;

public abstract class AbstractDateTimePrefixingUtility<T extends TemporalAccessor> {
    private final AbstractDateTimeUtility<T> dateTimeUtility;
    private int expectedFormatLength;
    public AbstractDateTimePrefixingUtility(String dateFormat, Class<T> temporalType) {
        this.dateTimeUtility = AbstractDateTimeUtility.createTemporalUtility(dateFormat, temporalType);
    }

    public AbstractDateTimePrefixingUtility(String dateFormat, Class<T> temporalType, int expectedFormatLength) {
        this.dateTimeUtility = AbstractDateTimeUtility.createTemporalUtility(dateFormat, temporalType);
        this.expectedFormatLength = expectedFormatLength;
    }

    public boolean isStringValidFormat(String s) {
        return dateTimeUtility.isStringValidFormat(s);
    }

    public boolean isStringAlreadyPrefixed(String s) {
        String maybeStringDate = getMaybeStringDate(s);
        if (maybeStringDate == null) {
            return false;
        }
        return dateTimeUtility.isStringValidFormat(maybeStringDate);
    }

    public T getPrefixedTemporal(String s) {
        String maybeStringDate = getMaybeStringDate(s);
        if (maybeStringDate == null) {
            return null;
        }
        return dateTimeUtility.parse(maybeStringDate);
    }

    public String prefixStringWithTemporal(String s, T temporal) {
        String formattedDate = dateTimeUtility.format(temporal);
        return formattedDate + s;
    }

    private String getMaybeStringDate(String s) {
        int patternLength;
        if (expectedFormatLength > 0 ) {
            patternLength = expectedFormatLength;
        } else {
            patternLength = dateTimeUtility.getPattern().length();
        }
        try {
            return s.substring(0, patternLength);
        } catch (StringIndexOutOfBoundsException e) {
            return null;
        }
    }
}
