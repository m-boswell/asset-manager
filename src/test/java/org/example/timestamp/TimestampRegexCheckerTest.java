package org.example.timestamp;

import org.example.timestamp.TimestampRegexChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimestampRegexCheckerTest {

    @Test
    void hasTimeStampPattern_returnsTrue_whenPatternMatches() {
        TimestampRegexChecker checker = new TimestampRegexChecker("\\d{4}-\\d{2}-\\d{2}T\\d{2}\\:\\d{2}\\:\\d{2}");
        assertTrue(checker.hasTimeStamp("2023-11-11T10:15:30"));
    }

    @Test
    void hasDatePattern_returnsTrue_whenPatternMatches() {
        TimestampRegexChecker checker = new TimestampRegexChecker("\\d{4}-\\d{2}-\\d{2}");
        assertTrue(checker.hasTimeStamp("2023-11-11"));
    }

    @Test
    void hasPrefixedTimeStampPattern_returnsTrue_whenPatternMatches() {
        TimestampRegexChecker checker = new TimestampRegexChecker("(\\d{4}-\\d{2}-\\d{2}T\\d{2}\\:\\d{2}\\:\\d{2}).*");
        assertTrue(checker.hasTimeStamp("2023-11-11T10:15:30abc"));
    }

    @Test
    void hasTimeStampPattern_returnsFalse_whenPatternDoesNotMatch() {
        TimestampRegexChecker checker = new TimestampRegexChecker("\\d{4}-\\d{2}-\\d{2}T\\d{2}\\:\\d{2}\\:\\d{2}");
        assertFalse(checker.hasTimeStamp("abc2023-11-11T10:15:30"));
    }

    @Test
    void hasPrefixedTimeStampPattern_returnsFalse_whenPatternDoesNotMatch() {
        TimestampRegexChecker checker = new TimestampRegexChecker("(\\d{4}-\\d{2}-\\d{2}T\\d{2}\\:\\d{2}\\:\\d{2}).*");
        assertTrue(checker.hasTimeStamp("2023-11-11T10:15:30abc"));
    }

    @Test
    void getTimeStampPattern_returnsPattern_whenPatternMatches() {
        TimestampRegexChecker checker = new TimestampRegexChecker("(\\d{4}-\\d{2}-\\d{2}T\\d{2}\\:\\d{2}\\:\\d{2})");
        assertEquals("2023-11-11T10:15:30", checker.getTimeStamp("2023-11-11T10:15:30abc"));
    }

    @Test
    void getTimeStampPattern_returnsNull_whenPatternDoesNotMatch() {
        TimestampRegexChecker checker = new TimestampRegexChecker("(\\d{4}-\\d{2}-\\d{2}T\\d{2}\\:\\d{2}\\:\\d{2})");
        assertNull(checker.getTimeStamp("abc2023-11-11"));
    }
}