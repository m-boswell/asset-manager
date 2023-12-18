import org.example.Utils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UtilsTest {

    @Test
    void testIsStringContainingDateExpression() {
        assertTrue(Utils.isStringContainingDateExpression("20230101_123456"));
        assertFalse(Utils.isStringContainingDateExpression("20230101-123456"));
    }

    @Test
    void testConvertStringToDate() throws ParseException {
        assertNotNull(Utils.convertStringToDate("20230101_123456"));
        assertThrows(ParseException.class, () -> Utils.convertStringToDate("invalid_date"));
    }

    @Test
    void testIsPrefixedWithISOTimestamp() {
        assertTrue(Utils.isPrefixedWithISOTimestamp("2023-01-01T12:34:56SomeOtherText"));
        assertFalse(Utils.isPrefixedWithISOTimestamp("SomeText2023-01-01T12:34:56"));
    }

    @Test
    void testCheckFile() {
        File mockFile = Mockito.mock(File.class);
        when(mockFile.exists()).thenReturn(true);
        when(mockFile.isDirectory()).thenReturn(false);
        when(mockFile.canRead()).thenReturn(true);
        when(mockFile.canWrite()).thenReturn(true);

        assertDoesNotThrow(() -> Utils.checkFile(mockFile));

        when(mockFile.exists()).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> Utils.checkFile(mockFile));
    }
}
