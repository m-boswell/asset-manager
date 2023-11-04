import org.example.Asset;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
class AssetTest {

    @Test
    void testBuilderAndAccessors() {
        String name = "example.mp4";
        String path = "/path/to/example.mp4";
        Date date = new Date();

        Asset asset = new Asset.Builder()
                .setName(name)
                .setPath(path)
                .setDate(date)
                .build();

        assertEquals(name, asset.getName());
        assertEquals(path, asset.getPath());
        assertEquals(date, asset.getDate());
    }

    @Test
    void testIsVideo() {
        Asset videoAsset = new Asset.Builder().setName("video.mp4").build();
        Asset imageAsset = new Asset.Builder().setName("image.jpg").build();

        assertTrue(videoAsset.isVideo());
        assertFalse(imageAsset.isVideo());
    }

    @Test
    void testToString() {
        Asset asset = new Asset.Builder()
                .setName("test.png")
                .setPath("/path/to/test.png")
                .setDate(new Date(0))
                .build();

        String expected = "Asset{name='test.png', path='/path/to/test.png', date=Wed Dec 31 19:00:00 EST 1969}";
        assertEquals(expected, asset.toString());
    }

    @Test
    void testFromFile() {
        File mockFile = createMockFile("video.mp4", "/path/to/video.mp4", 100000L);
        Asset asset = Asset.fromFile(mockFile);

        assertEquals("video.mp4", asset.getName());
        assertEquals("/path/to/video.mp4", asset.getPath());
        assertEquals(new Date(100000L), asset.getDate());
    }

    @Test
    void testFromFiles() {
        List<File> files = new ArrayList<>();
        files.add(createMockFile("first.mp4", "/path/to/first.mp4", 100000L));
        files.add(createMockFile("second.mp4", "/path/to/second.mp4", 200000L));

        List<Asset> assets = Asset.fromFiles(files);
    }

    // Helper method to create a mocked File instance
    private File createMockFile(String name, String path, long lastModified) {
        File file = mock(File.class);
        when(file.getName()).thenReturn(name);
        when(file.getAbsolutePath()).thenReturn(path);
        when(file.lastModified()).thenReturn(lastModified);
        return file;
    }
}