package org.example;

import java.io.File;
import java.util.Date;

public class Asset {
    private final String name;
    private final String path;
    private final Date date;

    // Private constructor
    private Asset(Builder builder) {
        this.name = builder.name;
        this.path = builder.path;
        this.date = builder.date;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", date=" + date +
                '}';
    }

    // Static Builder class
    public static class Builder {
        private String name;
        private String path;
        private Date date;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPath(String path) {
            this.path = path;
            return this;
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Asset build() {
            return new Asset(this);
        }
    }

    // Public function to return a File from the path
    public File getFileFromPath() {
        return new File(path);
    }

    // Static helper function to create Asset from File
    public static Asset fromFile(File file) {
        return new Builder()
                .setName(file.getName())
                .setPath(file.getAbsolutePath())
                .setDate(new Date(file.lastModified()))
                .build();
    }
}