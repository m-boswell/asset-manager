package org.example;

import java.io.File;
import java.util.Date;

public class FileAsset {
    File file;

    public FileAsset(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public Date getDate() {
        return new Date(file.lastModified());
    }
}
