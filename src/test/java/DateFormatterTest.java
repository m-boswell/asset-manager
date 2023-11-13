import org.example.utils.DateFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DateFormatterTest {
    private DateFormatter dateFormatter;

    @BeforeEach
    public void setUp() {
        // Initialize the DateFormatter with a date format and regex
        dateFormatter = new DateFormatter("yyyy-MM-dd", "\\d{4}-\\d{2}-\\d{2}");
    }

    @Test
    public void testIsStringValidFormat_ValidFormat_ReturnsTrue() {
        assertTrue(dateFormatter.isStringValidFormat("2023-12-09"));
    }

    @Test
    public void testIsStringValidFormat_InvalidFormat_ReturnsFalse() {
        assertFalse(dateFormatter.isStringValidFormat("2023/12/09"));
    }

    @Test
    public void testGetFormattedDate_ValidString_ReturnsLocalDate() throws ParseException {
        LocalDate expectedDate = LocalDate.of(2023, 12, 9);
        LocalDate resultDate = dateFormatter.getFormattedDate("2023-12-09");
        assertEquals(expectedDate, resultDate);
    }

    @Test
    public void testGetFormattedDate_InvalidString_ThrowsParseException() {
        assertThrows(ParseException.class, () -> {
            dateFormatter.getFormattedDate("2023/12/09");
        });
    }

    @Test
    public void testGetFormattedDateString_ValidLocalDate_ReturnsFormattedString() {
        LocalDate date = LocalDate.of(2023, 12, 9);
        String expectedFormattedString = "2023-12-09";
        String resultFormattedString = dateFormatter.getFormattedDateString(date);
        assertEquals(expectedFormattedString, resultFormattedString);
    }

    @Test
    public void testGetFormattedDateString_InvalidLocalDate_ThrowsRuntimeException() {
        LocalDate date = LocalDate.of(2023, 12, 9);
        // Use a different date format to make it invalid
        dateFormatter = new DateFormatter("yyyy/MM/dd", "\\d{4}-\\d{2}-\\d{2}");
        assertThrows(RuntimeException.class, () -> {
            dateFormatter.getFormattedDateString(date);
        });
    }
}
