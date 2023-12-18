package org.example.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public abstract class AbstractDateTimeUtility<T extends TemporalAccessor> {
    private final DateTimeFormatter formatter;
    private final String pattern;

    public AbstractDateTimeUtility(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
        this.pattern = pattern;
    }

    public abstract T parse(String s);

    protected DateTimeFormatter getFormatter() {
        return formatter;
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

    public String format(T temporal) {
        return formatter.format(temporal);
    }

    /**
     * Creates a new instance of AbstractDateTimeUtility for a specified temporal type.
     *
     * @param pattern      The date and time pattern for formatting and parsing.
     * @param temporalType The temporal type for which to create the utility instance (e.g., LocalDate or LocalDateTime).
     * @param <U>          The type of TemporalAccessor.
     * @return A new instance of AbstractDateTimeUtility specialized for the specified temporal type.
     * @throws IllegalArgumentException If the temporal type is unsupported.
     */
    @SuppressWarnings("unchecked")
    public static <U extends TemporalAccessor> AbstractDateTimeUtility<U> createTemporalUtility(String pattern, Class<U> temporalType) {
        if (temporalType.equals(LocalDate.class)) {
            return (AbstractDateTimeUtility<U>) new LocalDateUtility(pattern);
        } else if (temporalType.equals(LocalDateTime.class)) {
            return (AbstractDateTimeUtility<U>) new LocalDateTimeUtility(pattern);
        } else {
            throw new IllegalArgumentException("Unsupported temporal type: " + temporalType.getSimpleName());
        }
    }
}
