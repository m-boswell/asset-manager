import org.example.DatePrefix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class DatePrefixTest {

    private DatePrefix datePrefix;

    @BeforeEach
    public void setUp() {
        // Define a regular expression pattern for ISO 8601 timestamp prefix. Instead of colons this ISO 8601 variant uses dots.
        String isoTimestampPatternForRegexCheck = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}.\\d{2}.\\d{2}";
        datePrefix = new DatePrefix("yyyy-MM-dd'T'HH.mm.ss", isoTimestampPatternForRegexCheck, " ");
    }
    @Test
    public void testIsStringPrefixedWithValidString() {
        assertTrue(datePrefix.isStringPrefixed("2023-11-03T14.07.22 Hello, world!"));
    }
    @Test
    public void testIsStringPrefixedWithInValidString() {
        assertFalse(datePrefix.isStringPrefixed("Hello, world!"));
    }
    @Test
    public void testGetPrefixedDateWithValidString() {
        int year = 2023;
        int month = 10; // November (0-based index)
        int day = 3;
        int hour = 14;
        int minute = 7;
        int second = 22;
        // Create a Date object with the hard-coded values
        Date javaDate = new Date(year - 1900, month, day, hour, minute, second);
        // Define the input string
        String inputString = "2023-11-03T14.07.22 Hello, world!";
        // Get the actual date
        Date actualDate = datePrefix.getPrefixedDate(inputString);
        // Assert that the actual date is equal to the expected date
        assertEquals(actualDate, javaDate);
    }
    @Test
    public void testGetPrefixedDateWithInValidString() {
        // Define the input string
        String inputString = "Hello, world!";
        // Get the actual date
        Date actualDate = datePrefix.getPrefixedDate(inputString);
        // Assert that the actual date is null
        assertNull(actualDate);
    }
    @Test
    public void testPrefixStringWithDateFormat() {
        int year = 2023;
        int month = 10; // November (0-based index)
        int day = 3;
        int hour = 14;
        int minute = 7;
        int second = 22;
        // Create a Date object with the hard-coded values
        Date javaDate = new Date(year - 1900, month, day, hour, minute, second);
        // Define the expected string
        String expectedString = "2023-11-03T14.07.22 Hello, world!";
        // Get the actual string
        String actualString = datePrefix.prefixStringWithDateFormat("Hello, world!", javaDate);
        // Assert that the actual string is equal to the expected string
        assertEquals(actualString, expectedString);
    }
}