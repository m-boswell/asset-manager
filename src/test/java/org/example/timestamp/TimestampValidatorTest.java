package org.example.timestamp;

import org.example.timestamp.TimestampValidator;
import org.junit.jupiter.api.Test;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class TimestampValidatorTest {

    @Test
    void isValid_returnsTrue_whenValidTimestampProvided() {
        TimestampValidator validator = new TimestampValidator(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH.mm.ss"));
        assertTrue(validator.isValid("2023-11-11T10.15.30"));
    }

    @Test
    void isValid_returnsFalse_whenInvalidTimestampProvided() {
        TimestampValidator validator = new TimestampValidator(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH.mm.ss"));
        assertFalse(validator.isValid("2023-11-11T25.15.30"));
    }

    @Test
    void isValid_returnsFalse_whenNonTimestampStringProvided() {
        TimestampValidator validator = new TimestampValidator(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH.mm.ss"));
        assertFalse(validator.isValid("Not a timestamp"));
    }
}
