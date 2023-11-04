package org.example;

import java.io.File;

public class FileArchiveProcessor {
    String archivePath;
    public FileArchiveProcessor(String archivePath) {
        this.archivePath = archivePath;
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
