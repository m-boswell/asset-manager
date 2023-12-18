package org.example.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DateTimePrefixingUtilityTest {

    @Test
    void testIsStringValidFormat() {
        DateTimePrefixingUtility utility = new DateTimePrefixingUtility("yyyy-MM-dd'T'HH.mm.ss");
        assertTrue(utility.isStringValidFormat("2023-11-11T10.15.30"));
        assertFalse(utility.isStringValidFormat("abc2023-11-11T10.15.30"));
        assertFalse(utility.isStringValidFormat("2023/11/11T10.15.30abc"));
        assertFalse(utility.isStringValidFormat("2023-11-11T25.15.30")); // Invalid hour
    }

    @Test
    void testIsStringAlreadyPrefixed() {
        DateTimePrefixingUtility utility = new DateTimePrefixingUtility("yyyy-MM-dd'T'HH.mm.ss");
        assertTrue(utility.isStringAlreadyPrefixed("2023-11-11'T'10.15.30abc"));
        assertFalse(utility.isStringAlreadyPrefixed("abc2023-11-11T10.15.30"));
        assertFalse(utility.isStringAlreadyPrefixed("2023/11/11T10.15.30abc"));
        assertTrue(utility.isStringAlreadyPrefixed("2023-11-11T10.15.30"));
    }

    @Test
    void testGetPrefixedLocalDateTime() {
        DateTimePrefixingUtility utility = new DateTimePrefixingUtility("\\d{4}-\\d{2}-\\d{2}T\\d{2}\\.\\d{2}\\.\\d{2}");
        LocalDateTime expectedDateTime = LocalDateTime.of(2023, 11, 11, 10, 15, 30);
        LocalDateTime parsedDateTime = utility.getPrefixedTemporal("2023-11-11T10.15.30abc");
        assertEquals(expectedDateTime, parsedDateTime);
    }

    @Test
    void testPrefixStringWithLocalDateTime() {
        DateTimePrefixingUtility utility = new DateTimePrefixingUtility("yyyy-MM-dd'T'HH.mm.ss");
        LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 11, 10, 15, 30);
        String expectedResult = "2023-11-11T10.15.30abc";
        String prefixedString = utility.prefixStringWithTemporal("abc", localDateTime);
        assertEquals(expectedResult, prefixedString);
    }
}