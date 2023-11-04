package integration;

import org.example.Asset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssetTestIT {

    private File sampleVideoFile;
    private File loremIpsumFile;

    @BeforeEach
    void setUp() {
        sampleVideoFile = new File(getClass().getClassLoader().getResource("sample-video.mp4").getFile());
        loremIpsumFile = new File(getClass().getClassLoader().getResource("lorem-ipsum.txt").getFile());
        assertTrue(sampleVideoFile.exists(), "Sample video file must exist");
        assertTrue(loremIpsumFile.exists(), "Lorem ipsum file must exist");
    }

    @Test
    void testToFile() {
        Asset asset = new Asset.Builder()
                .setName("sample-video.mp4")
                .setPath(sampleVideoFile.getAbsolutePath())
                .setDate(new Date(sampleVideoFile.lastModified()))
                .build();

        File fileFromAsset = Asset.toFile(asset);
        assertNotNull(fileFromAsset, "File should not be null");
        assertTrue(fileFromAsset.exists(), "File from asset should exist");
        assertEquals(sampleVideoFile.getAbsolutePath(), fileFromAsset.getAbsolutePath(), "Paths should match");
    }

    @Test
    void testFromFile() {
        Asset asset = Asset.fromFile(sampleVideoFile);
        assertNotNull(asset, "Asset should not be null");
        assertEquals("sample-video.mp4", asset.getName(), "Names should match");
        assertEquals(sampleVideoFile.getAbsolutePath(), asset.getPath(), "Paths should match");
    }

    @Test
    void testFromFiles() {
        List<File> files = Arrays.asList(sampleVideoFile, loremIpsumFile);
        List<Asset> assets = Asset.fromFiles(files);

        assertNotNull(assets, "Assets should not be null");
        assertEquals(2, assets.size(), "Assets list should contain two assets");

        Asset videoAsset = assets.get(0);
        assertEquals("sample-video.mp4", videoAsset.getName(), "Video asset name should match");
        assertEquals(sampleVideoFile.getAbsolutePath(), videoAsset.getPath(), "Video asset path should match");

        Asset textAsset = assets.get(1);
        assertEquals("lorem-ipsum.txt", textAsset.getName(), "Text asset name should match");
        assertEquals(loremIpsumFile.getAbsolutePath(), textAsset.getPath(), "Text asset path should match");
    }
}
