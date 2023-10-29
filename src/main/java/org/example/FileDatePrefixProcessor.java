package org.example;

import java.io.File;

public class FileDatePrefixProcessor {

    public Asset process(Asset asset) {
        File file = asset.getFileFromPath();
        if (this.fileHasDateOrTimePrefix(file)) {
            return asset;
        }
        File prefixedFile = this.prefixFileNameWithCreatedTimeStamp(file);
        return Asset.fromFile(prefixedFile);
    }
    private boolean fileHasDateOrTimePrefix(File file) {
        return fileHasDateStampPrefix(file) || fileHasTimeStampPrefix(file);
    }
    private boolean fileHasDateStampPrefix(File file) {
        String fileName = file.getName();
        return fileName.matches("^\\d{4}-\\d{2}-\\d{2}.*");
    }

    private boolean fileHasTimeStampPrefix(File file) {
        String fileName = file.getName();
        return fileName.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*");
    }
    /**
     * Prefix the file name with the created date.
     *
     * @param file - the file to prefix
     */
    private File prefixFileNameWithCreatedTimeStamp(File file) {
        if (!file.exists()) {
            throw new RuntimeException("File to prefix with created at timestamp does not exist at " + file.getName());
        }
        // check if the file name is already prefixed with an ISO timestamp

        long createdDate = file.lastModified();
        String isoTimestamp = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH.mm.ss").format(new java.util.Date(createdDate));
        String fileName = file.getName();
        String newFileName = isoTimestamp + " " + fileName;
        File newFile = new File(file.getParent(), newFileName);
        boolean isRenamed = file.renameTo(newFile);
        if (!isRenamed) {
            throw new RuntimeException("Could not rename file to " + newFileName);
        }
        return newFile;
    }
}
