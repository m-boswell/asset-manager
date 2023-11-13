package org.example.utils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class DateFormatter {
    private final String dateFormat;
    private final String regex;

    public DateFormatter(String dateFormat, String regex) {
        this.dateFormat = dateFormat;
        this.regex = regex;
    }

    public boolean isStringValidFormat(String s) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(s).find();
    }

    public LocalDate getFormattedDate(String s) throws ParseException {
        if (!this.isStringValidFormat(s)) {
            throw new ParseException("String is not a valid format", 0);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        return LocalDate.parse(s, dtf);
    }

    public String getFormattedDateString(LocalDate date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        if (!this.isStringValidFormat(date.format(dtf))) {
            throw new RuntimeException("The date format and regex supplied do not match");
        }
        return date.format(dtf);
    }
}
