package org.example.utils;

import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimePrefixingUtility  {
    private final String regex;
    private final DateTimeFormatter formatter;

    public DateTimePrefixingUtility(String regex, DateTimeFormatter formatter) {
        this.regex = regex;
        this.formatter = formatter;
    }

    public boolean isPrefixValidTemporal(String s) {
        return false;
    }

    public boolean isPrefixed(String s) {
        Pattern regexPattern = Pattern.compile(this.regex);
        Matcher matcher = regexPattern.matcher(s);

        return matcher.matches();
    }

    public String getPrefixedTemporal(String s) {
        Pattern regexPattern = Pattern.compile(this.regex);
        Matcher matcher = regexPattern.matcher(s);

        if (matcher.find()) {
            return matcher.group(0);
        } else {
            return null; // Return null if no valid timestamp is found
        }
    }

//    public void prefixStringWithTemporal(String s, T temporal) {
//
//    }
}
