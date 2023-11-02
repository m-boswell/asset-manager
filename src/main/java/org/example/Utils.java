package org.example;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    /* Date Utils - START */
    public static boolean isStringContainingDateExpression(String s) {
        // Define the regular expression pattern for "yyyyMMdd_HHmmss"
        String pattern = "\\d{8}_\\d{6}";

        // Create a Pattern object from the pattern
        Pattern regex = Pattern.compile(pattern);

        // Use Matcher to match the pattern against the string
        Matcher matcher = regex.matcher(s);

        // Return true if the file name matches the pattern, false otherwise
        return matcher.matches();
    }

    public static Date convertStringToDate(String dateTimeStr) throws ParseException {
        // Define the input pattern to match the provided format
        SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss");

        // Parse the input string using the input pattern
        return inputFormatter.parse(dateTimeStr);
    }

    public static boolean isPrefixedWithISOTimestamp(String input) {
        // Define a regular expression pattern for ISO timestamps (YYYY-MM-DDTHH:mm:ss)
        String isoTimestampPattern = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";

        // Create a pattern object and matcher
        Pattern pattern = Pattern.compile("^" + isoTimestampPattern);
        Matcher matcher = pattern.matcher(input);

        // Check if the input string starts with an ISO timestamp
        return matcher.find();
    }

    /* Date Utils - END */

    /**
     * Convert a video file to an audio file.
     * @param inputFilePath - path to the input video file
     * @param outputFilePath - path to the output audio file
     * @throws IOException - if the input file does not exist
     * @throws InterruptedException - if the process is interrupted
     */
    public static void convertVideoToAudio(String inputFilePath, String outputFilePath) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("ffmpeg", "-i", inputFilePath, "-vn", "-acodec", "libmp3lame", outputFilePath);
        Process process = processBuilder.start();
        process.waitFor();
    }

    public static void checkFile(File file) {
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

    public static void checkFiles(List<File> files) {
        for (File file : files) {
            checkFile(file);
        }
    }

    /**
     * Wrap file in directory. This method should take a file name and put it in a directory with the same name.
     */
    public static String wrapFileInDirectory(File file) {
        String fileName = file.getName();
        String parentPath = file.getParent();
        String directoryPath = parentPath + "/" + fileName;
        File directory = new File(directoryPath);
        boolean isDirectoryCreated = directory.mkdir();
        if (!isDirectoryCreated) {
            throw new RuntimeException("Could not create directory " + directoryPath);
        }
        String filePath = directoryPath + "/" + fileName;
        Utils.moveFile(file.getAbsolutePath(), filePath);
        return filePath;
    }

    /**
     * Move a file to a new destination.
     */
    public static void moveFile(String sourcePath, String destinationPath) {
        File sourceFile = new File(sourcePath);
        File destinationFile = new File(destinationPath);
        boolean isMoved = sourceFile.renameTo(destinationFile);
        if (!isMoved) {
            throw new RuntimeException("Could not move file to " + destinationPath);
        }
    }

    /**
     * Returns a list of all files in the given directory.
     *
     * @param directoryPath the path of the directory
     * @return a list of files in the directory
     */
    public static List<File> getListFiles(String directoryPath) {
        List<File> fileList = new ArrayList<>();
        File directory = new File(directoryPath);

        // Check if the directory exists and is indeed a directory
        if (directory.exists() && directory.isDirectory()) {
            // Get all files and directories within the directory
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    // Add only files, exclude directories
                    if (file.isFile()) {
                        fileList.add(file);
                    }
                }
            }
        } else {
            System.out.println("The provided path is not a valid directory.");
        }

        return fileList;
    }

    public static String getDirectoryPathFromUserInput() {
        // Get user input for the directory path
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the directory path: ");
        String directoryPath = scanner.nextLine();
        scanner.close();
        return directoryPath;
    }

    public static String getArchivePathFromUserInput() {
        // Get user input for the archive path
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the archive path: ");
        String archivePath = scanner.nextLine();
        scanner.close();
        return archivePath;
    }
}
