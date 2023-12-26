package org.example.utils.datetime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to check if a given string matches a timestamp pattern.
 * The pattern is provided as a regular expression during the creation of an instance of this class.
 */
public class TimestampRegexChecker {
    private final String regex;

    /**
     * Constructs a new TimestampRegexChecker with the provided regular expression.
     *
     * @param regex the regular expression to be used for matching timestamps
     */
    public TimestampRegexChecker(String regex) {
        this.regex = regex;
    }

    /**
     * Checks if the provided string matches the timestamp pattern.
     *
     * @param s the string to be checked
     * @return true if the string matches the timestamp pattern, false otherwise
     */
    public boolean hasTimeStamp(String s) {
        Pattern regexPattern = Pattern.compile(this.regex);
        Matcher matcher = regexPattern.matcher(s);

        return matcher.matches();
    }

    /**
     * Extracts the timestamp from the provided string if it matches the timestamp pattern.
     *
     * @param input the string from which the timestamp is to be extracted
     * @return the extracted timestamp if the string matches the timestamp pattern, null otherwise
     */
    public String getTimeStamp(String input) {
        Pattern regexPattern = Pattern.compile(this.regex);
        Matcher matcher = regexPattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null; // Return null if no valid timestamp is found
    }
}
