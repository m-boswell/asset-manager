package org.example;

import org.example.asset.Asset;

import java.io.IOException;

public class FileVideoToAudioProcessor {
    /**
     * Process an asset that is a video file and convert it to an audio file.
     * @param asset - the asset to process
     * @return the processed asset
     */
    public Asset process(Asset asset) throws InterruptedException {
        if (!asset.isVideo()) {
            return asset;
        }
        String videoFilePath = asset.getPath();
        String audioFilePath = videoFilePath.replace(".mp4", ".mp3");
        try {
            Utils.convertVideoToAudio(videoFilePath, audioFilePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not convert video to audio", e);
        }
        return Asset.fromFile(new java.io.File(audioFilePath));
    }
}
