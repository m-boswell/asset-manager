package org.example.utils.datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import static org.junit.jupiter.api.Assertions.*;

public class TimestampPrefixerTest {
    @Test
    void addTimestamp_returnsStringWithTimestamp_whenTemporalAccessorIsValid() {
        TimestampPrefixer prefixer = TimestampPrefixer.createTimestampPrefixer("LocalDateTime");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        TemporalAccessor temporalAccessor = LocalDateTime.now();
        assert prefixer != null;
        String result = prefixer.addTimestamp("Hello", temporalAccessor);
        assertTrue(result.startsWith(formatter.format(temporalAccessor)));
    }

    @Test
    void getTimestamp_returnsTimestamp_whenStringContainsTimestamp() {
        TimestampPrefixer prefixer = TimestampPrefixer.createTimestampPrefixer("LocalDateTime");
        String timestamp = "2023-11-11T10:15:30";
        assert prefixer != null;
        String result = prefixer.getTimestamp(timestamp + " Hello");
        assertEquals(timestamp, result);
    }

    @Test
    void getTimestamp_returnsNull_whenStringDoesNotContainTimestamp() {
        TimestampPrefixer prefixer = TimestampPrefixer.createTimestampPrefixer("LocalDateTime");
        assert prefixer != null;
        String result = prefixer.getTimestamp("Hello");
        assertNull(result);
    }

    @Test
    void createTimestampPrefixer_returnsPrefixer_whenTypeIsValid() {
        TimestampPrefixer prefixer = TimestampPrefixer.createTimestampPrefixer("LocalDateTime");
        assertNotNull(prefixer);
    }

    @Test
    void createTimestampPrefixer_returnsNull_whenTypeIsInvalid() {
        TimestampPrefixer prefixer = TimestampPrefixer.createTimestampPrefixer("InvalidType");
        assertNull(prefixer);
    }

}