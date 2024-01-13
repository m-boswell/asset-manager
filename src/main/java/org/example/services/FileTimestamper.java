package org.example.services;

import org.example.utils.datetime.TimestampPrefixer;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class FileTimestamper {

    private final TimestampPrefixer timestampPrefixer;
    private final String type;

    public FileTimestamper(String type) {
        this.type = type;
        timestampPrefixer = TimestampPrefixer.createTimestampPrefixer(type);
        if (timestampPrefixer == null) {
            throw new IllegalArgumentException("Invalid type");
        }
    }

    public void stampFile(File file) {
        String path = file.getAbsolutePath();

        if (Objects.equals(this.type, "LocalDateTime")) {
            // do something
        } else if (Objects.equals(this.type, "LocalDate")) {
            // do something else
        }

        LocalDateTime lastModified = LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault());

        // add timestamp to beginning of file name by using timestampPrefixer.addTimestamp function.
        String newName = timestampPrefixer.addTimestamp(file.getName(), lastModified);

        // rename the file with the new name
        File newFile = new File(file.getParent(), newName);
        if (!file.renameTo(newFile)) {
            System.out.println("Could not rename file " + file.getName());
        }

    }
}
