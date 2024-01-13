package integration;

import org.example.timestamp.TimestampPrefixer;
import org.example.timestamp.services.FileTimestamper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileTimestamperTestIT {

    @TempDir
    Path tempDir;

    private FileTimestamper fileTimestamper;
    private TimestampPrefixer timestampPrefixer;

    @BeforeEach
    public void setup() {
        timestampPrefixer = TimestampPrefixer.createTimestampPrefixer("LocalDateTime");
        fileTimestamper = new FileTimestamper(timestampPrefixer);
    }

    @Test
    public void stampsFileWithTimestampWhenFileExists() throws IOException {
        File file = Files.createTempFile(tempDir, "test", ".txt").toFile();

        fileTimestamper.stampFile(file);

        File stampedFile = new File(tempDir.toFile(), timestampPrefixer.addTimestamp(file.getName(), LocalDateTime.now()));
        assertTrue(stampedFile.exists());
    }

    @Test
    public void throwsExceptionWhenFileDoesNotExist() {
        File file = new File(tempDir.toFile(), "nonexistent.txt");

        assertThrows(IllegalArgumentException.class, () -> {
            fileTimestamper.stampFile(file);
        });
    }

    @BeforeEach
    public void setupForDatestamp() {
        timestampPrefixer = TimestampPrefixer.createTimestampPrefixer("Datestamp");
        fileTimestamper = new FileTimestamper(timestampPrefixer);
    }

    @Test
    public void stampsFileWithDatestampWhenFileExists() throws IOException {
        File file = Files.createTempFile(tempDir, "test", ".txt").toFile();

        fileTimestamper.stampFile(file);

        File stampedFile = new File(tempDir.toFile(), timestampPrefixer.addTimestamp(file.getName(), LocalDateTime.now()));
        assertTrue(stampedFile.exists());
    }

    @Test
    public void throwsExceptionWhenFileDoesNotExistForDatestamp() {
        File file = new File(tempDir.toFile(), "nonexistent.txt");

        assertThrows(IllegalArgumentException.class, () -> {
            fileTimestamper.stampFile(file);
        });
    }
}