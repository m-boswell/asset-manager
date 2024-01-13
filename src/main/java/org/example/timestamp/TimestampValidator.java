package org.example.timestamp;

import java.time.format.DateTimeFormatter;

/**
 * This class is used to validate timestamps.
 * The timestamps are validated according to the provided DateTimeFormatter.
 */
public class TimestampValidator {
    private final DateTimeFormatter formatter;

    /**
     * Constructs a new TimestampValidator with the provided DateTimeFormatter.
     *
     * @param formatter the DateTimeFormatter to be used for validating timestamps
     */
    public TimestampValidator(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    /**
     * Checks if the provided string is a valid timestamp.
     * The string is considered valid if it can be parsed by the DateTimeFormatter provided during the creation of this TimestampValidator.
     *
     * @param s the string to be checked
     * @return true if the string is a valid timestamp, false otherwise
     */
    public boolean isValid(String s) {
        try {
            formatter.parse(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}