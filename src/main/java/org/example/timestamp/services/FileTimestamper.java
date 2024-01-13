package org.example.timestamp.services;

import org.example.timestamp.TimestampPrefixer;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class FileTimestamper {

    private final TimestampPrefixer timestampPrefixer;

    public FileTimestamper(TimestampPrefixer timestampPrefixer) {
        this.timestampPrefixer = timestampPrefixer;
    }

    public void stampFile(File file) {
        // check if the file exists if not throw an exception
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist");
        }
        LocalDateTime lastModified = LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault());
        String newName = timestampPrefixer.addTimestamp(file.getName(), lastModified);

        // rename the file with the new name
        File newFile = new File(file.getParent(), newName);
        if (!file.renameTo(newFile)) {
            System.out.println("Could not rename file " + file.getName());
        }

    }
}
