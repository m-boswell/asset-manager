package org.example.utils;

import java.time.LocalDate;

public class DatePrefixingUtility {
    private final LocalDateUtility localDateUtility;

    public DatePrefixingUtility(String dateFormat) {
        this.localDateUtility = new LocalDateUtility(dateFormat);
    }

    public boolean isStringValidFormat(String s) {
        return localDateUtility.isStringValidFormat(s);
    }

    public boolean isStringAlreadyPrefixed(String s) {
        String maybeStringDate = getMaybeStringDate(s);
        if (maybeStringDate == null) {
            return false;
        }
        return localDateUtility.isStringValidFormat(maybeStringDate);
    }

    public LocalDate getPrefixedLocalDate(String s) {
        String maybeStringDate = getMaybeStringDate(s);
        if (maybeStringDate == null) {
            return null;
        }
        return LocalDate.from(localDateUtility.parse(getMaybeStringDate(s)));
    }

    public String prefixStringWithLocalDate(String s, LocalDate localDate) {
        String formattedDate = localDateUtility.format(localDate);
        return formattedDate + s;
    }

    private String getMaybeStringDate(String s) {
        int patternLength = localDateUtility.getPattern().length();
        try {
            return s.substring(0, patternLength);
        } catch (StringIndexOutOfBoundsException e) {
            return null;
        }
    }
}