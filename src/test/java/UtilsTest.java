import org.example.Utils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

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
    void testConvertVideoToAudio() throws IOException, InterruptedException {
        // Since this method involves external process, it's tricky to test it without actually running the command.
        // You can test whether the correct command is formed and passed to the ProcessBuilder.
        // Actual command execution should be avoided in unit tests.
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

    @Test
    void testWrapFileInDirectory() {
        // Mocking file operations can be complex. You need to carefully mock each method call
        // For example, mock the file's getParent, getName, etc.
        // Then you need to mock the result of new File() calls within the method
    }

    @Test
    void testMoveFile() {
        // Similar to testWrapFileInDirectory, you need to mock file operations
        // You also need to mock the renameTo method of File
    }

    @Test
    void testGetListFiles() {
        File mockDirectory = Mockito.mock(File.class);
        File mockFile1 = Mockito.mock(File.class);
        File mockFile2 = Mockito.mock(File.class);

        when(mockDirectory.exists()).thenReturn(true);
        when(mockDirectory.isDirectory()).thenReturn(true);
        when(mockDirectory.listFiles()).thenReturn(new File[]{mockFile1, mockFile2});
        when(mockFile1.isFile()).thenReturn(true);
        when(mockFile2.isFile()).thenReturn(false); // This one is not a file

        List<File> files = Utils.getListFiles("some/path");
        assertEquals(1, files.size()); // Only mockFile1 should be returned
    }

    // Other methods like getDirectoryPathFromUserInput and getArchivePathFromUserInput are difficult to unit test
    // since they rely on user input. These methods can be refactored to accept a Scanner as a parameter for easier testing.
}
