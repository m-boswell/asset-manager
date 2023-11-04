package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * A class to prefix a string with a date format.
 */
public class DatePrefix {
    private final String dateFormat;
    private final String regex;
    private final String separator;

    /**
     * Create a new DatePrefix object.
     * @param dateFormat - the date format
     * @param regex - the regular expression to check if a string is prefixed with a date
     * @param separator - the separator between the date and the string
     */
    public DatePrefix(String dateFormat, String regex, String separator) {
        this.dateFormat = dateFormat;
        this.regex = regex;
        this.separator = separator;
    }

    public boolean isStringPrefixed(String s) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(s).find();
    }

    /**
     * Get the date from a prefixed string.
     * @param s - the prefixed string
     * @return the date
     */
    public Date getPrefixedDate(String s) {
        String[] split = s.split(separator);
        String dateTimePrefix = split[0];
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            return sdf.parse(dateTimePrefix);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Prefix a string with a date format.
     * @param s - the string to prefix
     * @param date - the date to prefix the string with
     * @return the prefixed string
     */
    public String prefixStringWithDateFormat(String s, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String dateTimePrefix = sdf.format(date);
        return dateTimePrefix + separator + s;
    }
}
