package org.example;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FileProcessor {
    public void setArchivePath(String archivePath) {
        this.archivePath = archivePath;
    }
    String archivePath;

    public String getArchivePath() {
        return archivePath;
    }
    public void process(String path) {
        File file = new File(path);
        //  if the path is blank throw an exception
        if (path.isBlank()) {
            throw new IllegalArgumentException("Path is blank");
        }
        this.fileCheck(file);

        if (!this.fileHasDateOrTimePrefix(file)) {
            this.prefixFileNameWithCreatedTimeStamp(file);
        }

        // get file date
        Date fileDate = this.getFileDate(file);

        // wrap file in directory
        String wrappedFilePath = this.wrapFileInDirectory(file);

        // check archive directory if it has a directory for the fileDate year and month the if the date is 2023-10-11 the path should be archive/2023/2023-10
        String archivePath = this.getArchivePath();
        String archiveYearPath = archivePath + "/" + fileDate.getYear();
        String archiveMonthPath = archiveYearPath + "/" + fileDate.getMonth();
        File archiveYearDirectory = new File(archiveYearPath);
        File archiveMonthDirectory = new File(archiveMonthPath);
        if (!archiveYearDirectory.exists()) {
            boolean isArchiveYearDirectoryCreated = archiveYearDirectory.mkdir();
            if (!isArchiveYearDirectoryCreated) {
                throw new RuntimeException("Could not create archive year directory " + archiveYearPath);
            }
        }
        if (!archiveMonthDirectory.exists()) {
            boolean isArchiveMonthDirectoryCreated = archiveMonthDirectory.mkdir();
            if (!isArchiveMonthDirectoryCreated) {
                throw new RuntimeException("Could not create archive month directory " + archiveMonthPath);
            }
        }
        // move file to archive directory
        String archiveFilePath = archiveMonthPath + "/" + file.getName();
        this.moveFile(wrappedFilePath, archiveFilePath);

        // convert video to audio
        String audioFilePath = archiveMonthPath + "/" + file.getName().replace(".mp4", ".mp3");
        try {
            this.convertVideoToAudio(archiveFilePath, audioFilePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not convert video to audio");
        } catch (InterruptedException e) {
            throw new RuntimeException("Could not convert video to audio");
        }




    }
    public Date getFileDate(File file) {
        // check if there is a file prefix
        if (this.fileHasDateOrTimePrefix(file)) {
            // if there is a file prefix return the date from the prefix
            return this.getDateFromPrefix(file);
        }
        return new Date(file.lastModified());
    }

    private Date getDateFromPrefix(File file) {
        String fileName = file.getName();
        String datePrefix = fileName.substring(0, 10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return Date.from(LocalDate.parse(datePrefix, formatter).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private void fileCheck(File file) {
        // if the file does not exist throw an exception
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist");
        }
        // if the file is a directory throw an exception
        if (file.isDirectory()) {
            throw new IllegalArgumentException("File is a directory");
        }
        // if the file is not readable throw an exception
        if (!file.canRead()) {
            throw new IllegalArgumentException("File is not readable");
        }
        // if the file is not writable throw an exception
        if (!file.canWrite()) {
            throw new IllegalArgumentException("File is not writable");
        }
    }



    /**
     * Wrap file in directory. This method should take a file name and put it in a directory with the same name.
     */
    private String wrapFileInDirectory(File file) {
        String fileName = file.getName();
        String parentPath = file.getParent();
        String directoryPath = parentPath + "/" + fileName;
        File directory = new File(directoryPath);
        boolean isDirectoryCreated = directory.mkdir();
        if (!isDirectoryCreated) {
            throw new RuntimeException("Could not create directory " + directoryPath);
        }
        String filePath = directoryPath + "/" + fileName;
        this.moveFile(file.getAbsolutePath(), filePath);
        return filePath;
    }

    /**
     * Move a directory to a new destination.
     */
    private void moveDirectory(String sourcePath, String destinationPath) {
        File sourceDirectory = new File(sourcePath);
        File destinationDirectory = new File(destinationPath);
        boolean isMoved = sourceDirectory.renameTo(destinationDirectory);
        if (!isMoved) {
            throw new RuntimeException("Could not move directory to " + destinationPath);
        }
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



    /**
     * Convert a video file to an audio file.
     * @param inputFilePath - path to the input video file
     * @param outputFilePath - path to the output audio file
     * @throws IOException - if the input file does not exist
     * @throws InterruptedException - if the process is interrupted
     */
    public void convertVideoToAudio(String inputFilePath, String outputFilePath) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("ffmpeg", "-i", inputFilePath, "-vn", "-acodec", "libmp3lame", outputFilePath);
        Process process = processBuilder.start();
        process.waitFor();
    }
}
