package integration;

import org.example.Asset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    void testToFile() {
        LocalDateTime fileTimestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(sampleVideoFile.lastModified()), ZoneId.systemDefault());
        Asset<LocalDateTime> asset = new Asset.Builder<LocalDateTime>()
                .setName("sample-video.mp4")
                .setPath(sampleVideoFile.getAbsolutePath())
                .setDate(fileTimestamp)
                .build();

        File fileFromAsset = Asset.toFile(asset);
        assertNotNull(fileFromAsset, "File should not be null");
        assertTrue(fileFromAsset.exists(), "File from asset should exist");
        assertEquals(sampleVideoFile.getAbsolutePath(), fileFromAsset.getAbsolutePath(), "Paths should match");
    }

    @Test
    void testFromFile() {
        Asset<LocalDateTime> asset = Asset.fromFile(sampleVideoFile);
        assertNotNull(asset, "Asset should not be null");
        assertEquals("sample-video.mp4", asset.getName(), "Names should match");
        assertEquals(sampleVideoFile.getAbsolutePath(), asset.getPath(), "Paths should match");
    }

    @Test
    void testFromFiles() {
        List<File> files = Arrays.asList(sampleVideoFile, loremIpsumFile);
        List<Asset<LocalDateTime>> assets = Asset.fromFiles(files);

        assertNotNull(assets, "Assets should not be null");
        assertEquals(2, assets.size(), "Assets list should contain two assets");

        Asset<LocalDateTime> videoAsset = assets.get(0);
        assertEquals("sample-video.mp4", videoAsset.getName(), "Video asset name should match");
        assertEquals(sampleVideoFile.getAbsolutePath(), videoAsset.getPath(), "Video asset path should match");

        Asset<LocalDateTime> textAsset = assets.get(1);
        assertEquals("lorem-ipsum.txt", textAsset.getName(), "Text asset name should match");
        assertEquals(loremIpsumFile.getAbsolutePath(), textAsset.getPath(), "Text asset path should match");
    }
}