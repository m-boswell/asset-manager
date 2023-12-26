import org.example.Asset;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AssetTest {

    @Test
    void getName_returnsCorrectName() {
        Asset<LocalDateTime> asset = new Asset.Builder<LocalDateTime>()
                .setName("test.mp4")
                .setPath("/path/to/test.mp4")
                .setDate(LocalDateTime.now())
                .build();

        assertEquals("test.mp4", asset.getName());
    }

    @Test
    void getPath_returnsCorrectPath() {
        Asset<LocalDateTime> asset = new Asset.Builder<LocalDateTime>()
                .setName("test.mp4")
                .setPath("/path/to/test.mp4")
                .setDate(LocalDateTime.now())
                .build();

        assertEquals("/path/to/test.mp4", asset.getPath());
    }

    @Test
    void getDate_returnsCorrectDate() {
        LocalDateTime date = LocalDateTime.now();
        Asset<LocalDateTime> asset = new Asset.Builder<LocalDateTime>()
                .setName("test.mp4")
                .setPath("/path/to/test.mp4")
                .setDate(date)
                .build();

        assertEquals(date, asset.getDate());
    }

    @Test
    void isVideo_returnsTrueForVideoFile() {
        Asset<LocalDateTime> asset = new Asset.Builder<LocalDateTime>()
                .setName("test.mp4")
                .setPath("/path/to/test.mp4")
                .setDate(LocalDateTime.now())
                .build();

        assertTrue(asset.isVideo());
    }

    @Test
    void isVideo_returnsFalseForNonVideoFile() {
        Asset<LocalDateTime> asset = new Asset.Builder<LocalDateTime>()
                .setName("test.txt")
                .setPath("/path/to/test.txt")
                .setDate(LocalDateTime.now())
                .build();

        assertFalse(asset.isVideo());
    }

    @Test
    void toFile_createsFileWithCorrectPath() {
        Asset<LocalDateTime> asset = new Asset.Builder<LocalDateTime>()
                .setName("test.mp4")
                .setPath("/path/to/test.mp4")
                .setDate(LocalDateTime.now())
                .build();

        File file = Asset.toFile(asset);

        assertEquals("/path/to/test.mp4", file.getPath());
    }

    @Test
    void fromFile_createsAssetWithCorrectValues() {
        File file = new File("/path/to/test.mp4");
        Asset<LocalDateTime> asset = Asset.fromFile(file);

        assertEquals("test.mp4", asset.getName());
        assertEquals("/path/to/test.mp4", asset.getPath());
        assertTrue(asset.getDate().isBefore(LocalDateTime.now()));
    }

    @Test
    void fromFile_createsAssetWithCurrentDateForFutureFile() {
        File futureFile = new File("/path/to/future.mp4") {
            @Override
            public long lastModified() {
                return Instant.now().plusSeconds(60).toEpochMilli();
            }
        };

        Asset<LocalDateTime> asset = Asset.fromFile(futureFile);

        assertTrue(asset.getDate().isBefore(LocalDateTime.now().plusSeconds(60)));
    }
}