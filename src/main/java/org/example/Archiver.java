package org.example;

import java.io.File;
import java.util.Date;

public class Archiver {
    String archivePath;
    public Archiver(String archivePath) {
        this.archivePath = archivePath;
    }
    public void process(Asset asset) {
        Date date = asset.getDate();
        String path = asset.getPath();
        // check archive directory if it has a directory for the fileDate year and month the if the date is 2023-10-11 the path should be archive/2023/2023-10
        String archivePath = this.archivePath;
        String archiveYearPath = archivePath + "/" + date.getYear();
        String archiveMonthPath = archiveYearPath + "/" + date.getMonth();
        File archiveYearDirectory = new File(archiveYearPath);
        File archiveMonthDirectory = new File(archiveMonthPath);
        if (!archiveYearDirectory.exists()) {
            // create archive year directory
            boolean isArchiveYearDirectoryCreated = archiveYearDirectory.mkdir();
            // throw exception if directory is not created
            if (!isArchiveYearDirectoryCreated) {
                throw new RuntimeException("Could not create archive year directory " + archiveYearPath);
            }
        }
        if (!archiveMonthDirectory.exists()) {
            // create archive month directory
            boolean isArchiveMonthDirectoryCreated = archiveMonthDirectory.mkdir();
            // throw exception if directory is not created
            if (!isArchiveMonthDirectoryCreated) {
                throw new RuntimeException("Could not create archive month directory " + archiveMonthPath);
            }
        }
        // get file from path
        File file = asset.getFileFromPath();
        // move file to archive directory
        String archiveFilePath = archiveMonthPath + "/" + file.getName();
        this.moveFile(path, archiveFilePath);
    }

    /**
     * Move a file to a new destination.
     */
    private void moveFile(String sourcePath, String destinationPath) {
        File sourceFile = new File(sourcePath);
        File destinationFile = new File(destinationPath);
        boolean isMoved = sourceFile.renameTo(destinationFile);
        if (!isMoved) {
            throw new RuntimeException("Could not move file to " + destinationPath);
        }
    }

}
