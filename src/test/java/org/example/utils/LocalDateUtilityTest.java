package org.example.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LocalDateUtilityTest {

    @Test
    void testIsStringValidFormat() {
        LocalDateUtility utility = new LocalDateUtility("yyyy-MM-dd");

        assertTrue(utility.isStringValidFormat("2023-11-11"));
        assertFalse(utility.isStringValidFormat("2023/11/11"));
        assertFalse(utility.isStringValidFormat("11-11-2023"));
        assertFalse(utility.isStringValidFormat("2023-13-11"));
    }

    @Test
    void testParse() {
        LocalDateUtility utility = new LocalDateUtility("yyyy-MM-dd");
        LocalDate expectedDate = LocalDate.of(2023, 11, 11);
        LocalDate parsedDate = LocalDate.from(utility.parse("2023-11-11"));

        assertEquals(expectedDate, parsedDate);
    }

    @Test
    void testFormat() {
        LocalDateUtility utility = new LocalDateUtility("yyyy-MM-dd");
        LocalDate date = LocalDate.of(2023, 11, 11);
        String expectedFormattedString = "2023-11-11";
        String formattedString = utility.format(date);

        assertEquals(expectedFormattedString, formattedString);
    }
}

