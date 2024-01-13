package integration;

import org.example.asset.Asset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class AssetTestIT {

    private File sampleVideoFile;
    private File loremIpsumFile;

    @BeforeEach
    void setUp() {
        sampleVideoFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("sample-video.mp4"), "Sample video file must exist").getFile());
        loremIpsumFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("lorem-ipsum.txt"), "Lorem ipsum file must exist").getFile());
    }

    @Test
    void assetCreationFromFile() {
        Asset<LocalDateTime> asset = Asset.fromFile(sampleVideoFile);

        assertNotNull(asset, "Asset should not be null");
        assertEquals("sample-video.mp4", asset.getName(), "Names should match");
        assertEquals(sampleVideoFile.getAbsolutePath(), asset.getPath(), "Paths should match");
    }

    @Test
    void assetCreationFromNonExistingFile() {
        File nonExistingFile = new File("non-existing-file.txt");
        assertThrows(IllegalArgumentException.class, () -> {
            Asset.fromFile(nonExistingFile);
        }, "Asset creation from non-existing file should throw IllegalArgumentException");
    }

    @Test
    void assetCreationFromFiles() {
        List<File> files = Arrays.asList(sampleVideoFile, loremIpsumFile);
        List<Asset<LocalDateTime>> assets = Asset.fromFiles(files);

        assertNotNull(assets, "Assets should not be null");
        assertEquals(2, assets.size(), "Assets size should match");
        assertEquals("sample-video.mp4", assets.get(0).getName(), "Names should match");
        assertEquals(sampleVideoFile.getAbsolutePath(), assets.get(0).getPath(), "Paths should match");
        assertEquals("lorem-ipsum.txt", assets.get(1).getName(), "Names should match");
        assertEquals(loremIpsumFile.getAbsolutePath(), assets.get(1).getPath(), "Paths should match");
    }

    @Test
    void assetCreationFromFilesWithNonExistingFile() {
        File nonExistingFile = new File("non-existing-file.txt");
        List<File> files = Arrays.asList(sampleVideoFile, nonExistingFile);

        assertThrows(IllegalArgumentException.class, () -> {
            Asset.fromFiles(files);
        }, "Asset creation from files with non-existing file should throw IllegalArgumentException");
    }
}