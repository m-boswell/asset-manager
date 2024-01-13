package org.example.asset;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an Asset with a name, path and date.
 * The date is of a generic type T that extends Temporal.
 */
public class Asset<T extends Temporal>  {
    private final String name;
    private final String path;
    private final T date;

    /**
     * Private constructor for Asset.
     *
     * @param builder the Builder object used to construct this Asset
     */
    private Asset(Builder<T> builder) {
        this.name = builder.name;
        this.path = builder.path;
        this.date = builder.date;
    }

    /**
     * Getter for name.
     *
     * @return the name of the Asset
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for path.
     *
     * @return the path of the Asset
     */
    public String getPath() {
        return path;
    }

    /**
     * Getter for date.
     *
     * @return the date of the Asset
     */
    public T getDate() {
        return date;
    }

    /**
     * Checks if the Asset is a video.
     *
     * @return true if the Asset is a video, false otherwise
     */
    public boolean isVideo() {
        return this.name.endsWith(".mp4");
    }

    /**
     * Returns a string representation of the Asset.
     *
     * @return a string representation of the Asset
     */
    @Override
    public String toString() {
        return "Asset{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", date=" + date +
                '}';
    }

    /**
     * Static Builder class for Asset.
     */
    public static class Builder<T extends Temporal> {
        private String name;
        private String path;
        private T date;

        /**
         * Setter for name.
         *
         * @param name the name to set
         * @return the Builder instance
         */
        public Builder<T> setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Setter for path.
         *
         * @param path the path to set
         * @return the Builder instance
         */
        public Builder<T> setPath(String path) {
            this.path = path;
            return this;
        }

        /**
         * Setter for date.
         *
         * @param date the date to set
         * @return the Builder instance
         */
        public Builder<T> setDate(T date) {
            this.date = date;
            return this;
        }

        /**
         * Builds an Asset instance.
         *
         * @return the built Asset instance
         */
        public Asset<T> build() {
            return new Asset<>(this);
        }
    }

    /**
     * Creates an Asset from a File.
     *
     * @param file the File to convert
     * @return the created Asset
     */
    public static Asset<LocalDateTime> fromFile(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist at " + file.getAbsolutePath());
        }
        LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault());
        return new Builder<LocalDateTime>()
                .setName(file.getName())
                .setPath(file.getAbsolutePath())
                .setDate(date)
                .build();
    }

    /**
     * Creates a list of Assets from a list of Files.
     *
     * @param files the list of Files to convert
     * @return the list of created Assets
     */
    public static List<Asset<LocalDateTime>> fromFiles(List<File> files) {
        List<Asset<LocalDateTime>> assets = new ArrayList<>();
        for (File file : files) {
            assets.add(Asset.fromFile(file));
        }
        return assets;
    }
}