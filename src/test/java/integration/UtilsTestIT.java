package integration;

import org.example.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilsTestIT {

    private File sampleVideoFile;
    private File loremIpsumFile;

    @BeforeEach
    void setUp() {
        sampleVideoFile = new File(getClass().getClassLoader().getResource("sample-video.mp4").getFile());
        loremIpsumFile = new File(getClass().getClassLoader().getResource("lorem-ipsum.txt").getFile());
        assertTrue(sampleVideoFile.exists(), "Sample video file must exist");
        assertTrue(loremIpsumFile.exists(), "Lorem ipsum file must exist");
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clean up the test directory after each test
//        Files.deleteIfExists(Paths.get(TEST_OUTPUT_AUDIO_FILE));
//        Files.walk(Paths.get(TEST_DIRECTORY))
//                .sorted(Paths.get(TEST_DIRECTORY).reversed())
//                .map(Path::toFile)
//                .forEach(File::delete);
    }

    @Test
    void testConvertVideoToAudio() throws IOException, InterruptedException {
        String testOutputAudioFilePath = System.getProperty("java.io.tmpdir") + "/sample-video.mp3";
        Utils.convertVideoToAudio(sampleVideoFile.getPath(), testOutputAudioFilePath);
        File audioFile = new File(testOutputAudioFilePath);
        assertTrue(audioFile.exists(), "Audio file should be created");
    }

    @Test
    void testCheckFile() {
        File testFile = loremIpsumFile;
        assertDoesNotThrow(() -> Utils.checkFile(testFile), "Check on a valid file should not throw");
    }

    @Test
    @Disabled
    void testWrapFileInDirectory() {
        File testFile = loremIpsumFile;
        String wrappedFilePath = Utils.wrapFileInDirectory(testFile);
        assertTrue(new File(wrappedFilePath).exists(), "Wrapped file should exist");
    }

    @Test
    @Disabled
    void testMoveFile() {
        // Ensure that the move operation can be tested
//        String sourcePath = TEST_DIRECTORY + "movable-text-file.txt";
//        String destinationPath = TEST_DIRECTORY + "moved-text-file.txt";
//
//        try {
//            Files.copy(Paths.get(TEST_TEXT_FILE), Paths.get(sourcePath));
//            Utils.moveFile(sourcePath, destinationPath);
//            assertTrue(Files.exists(Paths.get(destinationPath)), "File should be moved to the new location");
//            assertFalse(Files.exists(Paths.get(sourcePath)), "Original file should no longer exist");
//        } catch (IOException e) {
//            fail("An IOException occurred: " + e.getMessage());
//        }
    }

    @Test
    @Disabled
    void testGetListFiles() {
        // Assuming the TEST_DIRECTORY is already populated with some files
//        List<File> files = Utils.getListFiles(TEST_DIRECTORY);
//        assertFalse(files.isEmpty(), "Should retrieve list of files");
    }

    // Note: The methods getDirectoryPathFromUserInput and getArchivePathFromUserInput cannot be effectively tested
    // in an integration test as they rely on user input. These would typically require manual testing or could be
    // refactored to allow for automation.
}
