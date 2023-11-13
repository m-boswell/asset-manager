package org.example.utils;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public abstract class AbstractDateTimeUtility<T extends TemporalAccessor> {
    private final DateTimeFormatter formatter;
    private final String pattern;

    public AbstractDateTimeUtility(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public boolean isStringValidFormat(String s) {
        try {
            parse(s);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    public T parse(String s) {
        try {
            return (T) formatter.parse(s);
        } catch (DateTimeException e) {
            throw new DateTimeException("Failed to parse the string: " + s, e);
        }
    }

    public String format(T temporal) {
        return formatter.format(temporal);
    }
}

