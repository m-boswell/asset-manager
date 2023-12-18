package org.example.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DatePrefixingUtilityTest {

    @Test
    void testIsStringValidFormat() {
        DatePrefixingUtility utility = new DatePrefixingUtility("yyyy-MM-dd");
        assertTrue(utility.isStringValidFormat("2023-11-11"));
        assertFalse(utility.isStringValidFormat("abc2023-11-11"));
        assertFalse(utility.isStringValidFormat("2023/11/11abc"));
        assertFalse(utility.isStringValidFormat("2023-13-11"));
    }

    @Test
    void testIsStringAlreadyPrefixed() {
        DatePrefixingUtility utility = new DatePrefixingUtility("yyyy-MM-dd");
        assertTrue(utility.isStringAlreadyPrefixed("2023-11-11abc"));
        assertFalse(utility.isStringAlreadyPrefixed("abc2023-11-11"));
        assertFalse(utility.isStringAlreadyPrefixed("2023/11/11abc"));
        assertTrue(utility.isStringAlreadyPrefixed("2023-11-11"));
    }

    @Test
    void testGetPrefixedLocalDate() {
        DatePrefixingUtility utility = new DatePrefixingUtility("yyyy-MM-dd");
        LocalDate expectedDate = LocalDate.of(2023, 11, 11);
        LocalDate parsedDate = utility.getPrefixedTemporal("2023-11-11abc");
        assertEquals(expectedDate, parsedDate);
    }

    @Test
    void testPrefixStringWithLocalDate() {
        DatePrefixingUtility utility = new DatePrefixingUtility("yyyy-MM-dd");
        LocalDate localDate = LocalDate.of(2023, 11, 11);
        String expectedResult = "2023-11-11abc";
        String prefixedString = utility.prefixStringWithTemporal("abc", localDate);
        assertEquals(expectedResult, prefixedString);
    }
}
