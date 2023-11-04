package integration;

import org.example.Asset;
import org.example.FileDatePrefixProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileDatePrefixProcessorTestIT {
    private FileDatePrefixProcessor processor;

    @BeforeEach
    public void setup() {
        processor = new FileDatePrefixProcessor();
    }
    @Test
    public void process_WithDateExpressionInFileName_ShouldNotChangeFileName() throws IOException, ParseException {
        // arrange - create a temp file with a date expression in the file name
        File tempFile = File.createTempFile("2023-10-01 test-FileDatePrefixProcessorTestIT",".txt");
        tempFile.deleteOnExit();
        String fileNameTest = tempFile.getName();
        Asset asset = Asset.fromFile(tempFile);
        // act
        Asset processedAsset = processor.process(asset);
        // assert - the file name should not change
        assertEquals(fileNameTest, tempFile.getName());
    }

    @Test
    public void process_WithTimestampExpressionInFileName_ShouldNotChangeFileName() throws IOException, ParseException {
        // arrange - create a temp file with a timestamp expression in the file name
        File tempFile = File.createTempFile("2023-11-03T14.07.22 test-FileDatePrefixProcessorTestIT",".txt");
        tempFile.deleteOnExit();
        String fileNameTest = tempFile.getName();
        Asset asset = Asset.fromFile(tempFile);
        // act
        Asset processedAsset = processor.process(asset);
        // assert - the file name should not change
        assertEquals(fileNameTest, tempFile.getName());
    }

    @Test
    public void process_WithNoTimestampExpressionInFileName_ShouldPrefixFileName() throws ParseException, IOException {
        // arrange - create a temp file with no timestamp expression in the file name
        File tempFile = File.createTempFile("test-FileDatePrefixProcessorTestIT",".txt");
        tempFile.deleteOnExit();
        String fileNameTest = tempFile.getName();
        String expectedProcessedFileName = getExpectedProcessedFileNameWithNoTimestampExpressionInFileName(tempFile);
        Asset asset = Asset.fromFile(tempFile);
        // act
        Asset processedAsset = processor.process(asset);
        File processedFile = Asset.toFile(processedAsset);
        // assert - the file name should change
        assertEquals(expectedProcessedFileName, processedFile.getName());
    }

    /**
     * Helper function for process_WithNoTimestampExpressionInFileName_ShouldPrefixFileName
     * Get the expected processed file name with no date expression in the file name
     * @param tempFile - the temp file
     * @return the expected processed file name
     */
    private static String getExpectedProcessedFileNameWithNoTimestampExpressionInFileName(File tempFile) {
        String fileNameTest = tempFile.getName();
        // Format the Date object to an ISO timestamp string
        long lastModifiedTime = tempFile.lastModified();
        // Convert the last modified time to a Date object
        Date lastModifiedDate = new Date(lastModifiedTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH.mm.ss");
        String isoTimestamp = sdf.format(lastModifiedDate);
        // prefix the expected file name with the ISO timestamp
        return isoTimestamp + " " + fileNameTest;
    }


}
