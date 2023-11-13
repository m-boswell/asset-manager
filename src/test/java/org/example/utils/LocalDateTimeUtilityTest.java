package org.example.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class LocalDateTimeUtilityTest {

    @Test
    void testIsStringValidFormat() {
        LocalDateTimeUtility utility = new LocalDateTimeUtility("yyyy-MM-dd HH:mm:ss");

        assertTrue(utility.isStringValidFormat("2023-11-11 12:34:56"));
        assertFalse(utility.isStringValidFormat("2023/11/11 12:34:56"));
        assertFalse(utility.isStringValidFormat("11-11-2023 12:34:56"));
        assertFalse(utility.isStringValidFormat("2023-13-11 12:34:56"));
    }

    @Test
    void testParse() {
        LocalDateTimeUtility utility = new LocalDateTimeUtility("yyyy-MM-dd HH:mm:ss");
        LocalDateTime expectedDateTime = LocalDateTime.of(2023, 11, 11, 12, 34, 56);
        LocalDateTime parsedDateTime = LocalDateTime.from(utility.parse("2023-11-11 12:34:56"));

        assertEquals(expectedDateTime, parsedDateTime);
    }

    @Test
    void testFormat() {
        LocalDateTimeUtility utility = new LocalDateTimeUtility("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.of(2023, 11, 11, 12, 34, 56);
        String expectedFormattedString = "2023-11-11 12:34:56";
        String formattedString = utility.format(dateTime);

        assertEquals(expectedFormattedString, formattedString);
    }
}


