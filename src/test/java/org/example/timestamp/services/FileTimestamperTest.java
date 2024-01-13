package org.example.timestamp.services;

import org.example.timestamp.TimestampPrefixer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

public class FileTimestamperTest {

    private FileTimestamper fileTimestamper;
    private TimestampPrefixer timestampPrefixer;

    @BeforeEach
    public void setup() {
        timestampPrefixer = Mockito.mock(TimestampPrefixer.class);
        fileTimestamper = new FileTimestamper(timestampPrefixer);
    }

    @Test
    public void stampsFileWithTimestampWhenFileExists() {
        File file = Mockito.mock(File.class);
        when(file.exists()).thenReturn(true);
        when(file.lastModified()).thenReturn(System.currentTimeMillis());
        when(file.getName()).thenReturn("test.txt");
        when(file.getAbsolutePath()).thenReturn("/path/to/test.txt");
        when(file.getParent()).thenReturn("/path/to");
        when(file.renameTo(Mockito.any(File.class))).thenReturn(true);
        when(timestampPrefixer.addTimestamp(Mockito.anyString(), Mockito.any(LocalDateTime.class))).thenReturn("2023-11-11_test.txt");

        fileTimestamper.stampFile(file);

        Mockito.verify(file).renameTo(new File("/path/to", "2023-11-11_test.txt"));
    }

    @Test
    public void throwsExceptionWhenFileDoesNotExist() {
        File file = Mockito.mock(File.class);
        when(file.exists()).thenReturn(false);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            fileTimestamper.stampFile(file);
        });

        Mockito.verify(file, Mockito.never()).renameTo(Mockito.any(File.class));
    }

    @Test
    public void doesNotStampFileWhenRenameFails() {
        File file = Mockito.mock(File.class);
        when(file.exists()).thenReturn(true);
        when(file.lastModified()).thenReturn(System.currentTimeMillis());
        when(file.getName()).thenReturn("test.txt");
        when(file.getAbsolutePath()).thenReturn("/path/to/test.txt");
        when(file.getParent()).thenReturn("/path/to");
        when(file.renameTo(Mockito.any(File.class))).thenReturn(false);
        when(timestampPrefixer.addTimestamp(Mockito.anyString(), Mockito.any(LocalDateTime.class))).thenReturn("2023-11-11_test.txt");

        fileTimestamper.stampFile(file);

        Mockito.verify(file).renameTo(new File("/path/to", "2023-11-11_test.txt"));
    }
}
