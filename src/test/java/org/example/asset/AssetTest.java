package org.example.asset;

import org.junit.jupiter.api.Test;

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

}