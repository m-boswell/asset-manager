package org.example;

import java.io.File;

public class Archiver {
    String archivePath;
    public Archiver(String archivePath) {
        this.archivePath = archivePath;
    }
//    public process(String path) {
//        File file = new File(path);
        // check if the file prefix has a date or time
//        if (this.fileHasDateOrTimePrefix(file)) {
//            // if the file prefix has a date or time, move the file to the archive path
//            this.moveFileToArchive(file);
//        }
//    }

    public static void moveFileToArchive(File file) {

    }

    public String getArchivePath() {
        return archivePath;
    }

}
