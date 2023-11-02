package org.example;

import java.io.File;
import java.util.Date;

public class FileDateArchiveProcessor {
    String archivePath;
    public FileDateArchiveProcessor(String archivePath) {
        this.archivePath = archivePath;
    }
    public void process(Asset asset) {
        Date date = asset.getDate();
        String path = asset.getPath();
        // check archive directory if it has a directory for the fileDate year and month. If the date is 2023-10-11 the path should be archive/2023/2023-10
        String archivePath = this.archivePath;
        String archiveYearPath = archivePath + "/" + date.getYear();
        String archiveMonthPath = archiveYearPath + "/" + date.getMonth();
        this.createArchiveYearDirectoryIfItDoesNotExist(archiveYearPath);
        this.createArchiveMonthDirectoryIfItDoesNotExist(archiveMonthPath);
        // get file from path
        File file = asset.getFileFromPath();
        // move file to archive directory
        String archiveFilePath = archiveMonthPath + "/" + file.getName();
        Utils.moveFile(path, archiveFilePath);
    }

    /**
     * Create archive year directory if it does not exist
     *
     * @param archiveYearPath - the archive year path
     */
    private void createArchiveYearDirectoryIfItDoesNotExist(String archiveYearPath) {
        File archiveYearDirectory = new File(archiveYearPath);
        if (!archiveYearDirectory.exists()) {
            boolean isArchiveYearDirectoryCreated = archiveYearDirectory.mkdir();
            if (!isArchiveYearDirectoryCreated) {
                throw new RuntimeException("Could not create archive year directory " + archiveYearPath);
            }
        }
    }

    /**
     * Create archive month directory if it does not exist
     */
    private void createArchiveMonthDirectoryIfItDoesNotExist(String archiveMonthPath) {
        File archiveMonthDirectory = new File(archiveMonthPath);
        if (!archiveMonthDirectory.exists()) {
            boolean isArchiveMonthDirectoryCreated = archiveMonthDirectory.mkdir();
            if (!isArchiveMonthDirectoryCreated) {
                throw new RuntimeException("Could not create archive month directory " + archiveMonthPath);
            }
        }
    }
}
