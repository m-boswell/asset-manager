package org.example.utils.datetime;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * This class is used to prefix a given string with a timestamp.
 * The timestamp is formatted according to the provided DateTimeFormatter.
 * The class also provides functionality to extract a timestamp from a string.
 */
public class TimestampPrefixer {
    private final DateTimeFormatter formatter;
    private final TimestampValidator timestampValidator;
    private final TimestampRegexChecker timestampRegexChecker;

    /**
     * Constructs a new TimestampPrefixer with the provided DateTimeFormatter and TimestampRegexChecker.
     *
     * @param formatter the DateTimeFormatter to be used for formatting timestamps
     * @param timestampRegexChecker the TimestampRegexChecker to be used for checking if a string contains a timestamp
     */
    public TimestampPrefixer(DateTimeFormatter formatter, TimestampRegexChecker timestampRegexChecker) {
        this.formatter = formatter;
        this.timestampValidator = new TimestampValidator(formatter);
        this.timestampRegexChecker = timestampRegexChecker;
    }

    /**
     * Adds a timestamp to the beginning of the provided string.
     * The timestamp is formatted according to the DateTimeFormatter provided during the creation of this TimestampPrefixer.
     *
     * @param s the string to which the timestamp is to be added
     * @param temporalAccessor the TemporalAccessor to be used for generating the timestamp
     * @return the string prefixed with the timestamp
     */
    public String addTimestamp(String s, TemporalAccessor temporalAccessor) {
        String timestamp = this.formatter.format(temporalAccessor);
        return timestamp + " " + s;
    }

    /**
     * Extracts the timestamp from the provided string if it matches the timestamp pattern.
     *
     * @param s the string from which the timestamp is to be extracted
     * @return the extracted timestamp if the string matches the timestamp pattern, null otherwise
     */
    public String getTimestamp(String s) {
        String maybeTimestamp = this.timestampRegexChecker.getTimeStamp(s);
        if (maybeTimestamp != null && this.timestampValidator.isValid(maybeTimestamp)) {
            return maybeTimestamp;
        }
        return null;
    }

    /**
     * Creates a new TimestampPrefixer based on the provided type.
     * If the type is "LocalDate", the TimestampPrefixer will use a DateTimeFormatter with the pattern "yyyy-MM-dd".
     * If the type is "LocalDateTime", the TimestampPrefixer will use a DateTimeFormatter with the pattern "yyyy-MM-dd'T'HH:mm:ss".
     *
     * @param type the type of TimestampPrefixer to be created
     * @return a new TimestampPrefixer of the specified type, or null if the type is not recognized
     */
    public static TimestampPrefixer createTimestampPrefixer(String type) {
        if (type.equals("LocalDate")) {
            return new TimestampPrefixer(DateTimeFormatter.ofPattern(Constants.DATE_PREFIX_FORMAT), new TimestampRegexChecker(Constants.DATE_PREFIX_REGEX));
        } else if (type.equals("LocalDateTime")) {
            return new TimestampPrefixer(DateTimeFormatter.ofPattern(Constants.TIMESTAMP_PREFIX_FORMAT), new TimestampRegexChecker(Constants.TIMESTAMP_PREFIX_REGEX));
        } else {
            return null;
        }
    }

}
