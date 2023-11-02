package org.example;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FileDatePrefixProcessor {

    /**
     * Process an asset and prefix the file name with the last modified date if it does not already have a date or time prefix. If the file name contains a date expression, prefix the file name with the date expression instead.
     * @param asset - the asset to process
     * @return the processed asset
     * @throws ParseException - if the date cannot be parsed
     */
    public Asset process(Asset asset) throws ParseException {
        File file = asset.getFileFromPath();
        String fileName = file.getName();
        if (this.stringHasDateOrTimePrefix(fileName)) {
            return asset;
        }
        if (Utils.isStringContainingDateExpression(fileName)) {
            Date date = Utils.convertStringToDate(fileName);
            File prefixedFile = this.prefixFileNameWithDate(file, date);
            return Asset.fromFile(prefixedFile);
        }
        // if the file name does not contain a date expression, prefix it with the last time the file was modified.
        Date date = new Date(file.lastModified());
        File prefixedFile = this.prefixFileNameWithDate(file, date);
        return Asset.fromFile(prefixedFile);
    }

    /**
     * Process an asset file after prompting the user and obtaining consent.
     * @param asset - the asset to process
     * @return the processed asset if the user accepts, or the original asset otherwise
     * @throws ParseException - if the date cannot be parsed
     */
    public Asset processWithPrompt(Asset asset) throws ParseException {
        File file = asset.getFileFromPath();

        // Describe what will happen
        String message = getProcessingMessage(file);
        System.out.println(message);

        boolean isConsent = getUserConsent();

        if (isConsent) {
            return process(asset); // Process the file if user agrees
        } else {
            System.out.println("No changes were made to the file.");
            return asset; // Return the original asset if user disagrees
        }
    }

    private boolean getUserConsent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to proceed with these changes? (yes/no): ");
        String userInput = scanner.nextLine();
        return "yes".equalsIgnoreCase(userInput);
    }

    /**
     * Constructs a message describing what will happen to the file.
     * @param file - the file to be processed
     * @return a String message
     */
    private String getProcessingMessage(File file) throws ParseException {
        String fileName = file.getName();
        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (stringHasDateOrTimePrefix(fileName) || !file.exists()) {
            return "The file '" + fileName + "' will not be modified.";
        } else if (Utils.isStringContainingDateExpression(fileName)) {
            date = Utils.convertStringToDate(fileName);
        } else {
            date = new Date(file.lastModified());
        }

        return "The file '" + fileName + "' will be prefixed with the date: " + dateFormat.format(date) + "so the new file name will be: " + date + " " + fileName;
    }

    /**
     * Check if a string has a date or time prefix
     * @param s - the string to check
     * @return true if the string has a date or time prefix, false otherwise
     */
    private boolean stringHasDateOrTimePrefix(String s) {
        return stringHasDateStampPrefix(s) || stringHasTimeStampPrefix(s);
    }

    /**
     * Check if a string has a date stamp prefix
     * @param s - the string to check
     * @return true if the string has a date stamp prefix, false otherwise
     */
    private boolean stringHasDateStampPrefix(String s) {
        return s.matches("^\\d{4}-\\d{2}-\\d{2}.*");
    }

    /**
     * Check if a string has a timestamp prefix
     * @param s - the string to check
     * @return true if the string has a timestamp prefix, false otherwise
     */
    private boolean stringHasTimeStampPrefix(String s) {
        return s.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*");
    }

    /**
     * Prefix file name with date
     * @param file - the file to prefix
     * @param date - the date to prefix the file name with
     * @return the prefixed file
     */
    private File prefixFileNameWithDate(File file, Date date) {
        if (!file.exists()) {
            throw new RuntimeException("File to prefix with date does not exist at " + file.getName());
        }
        // check if the file name is already prefixed with an ISO timestamp
        String fileName = file.getName();
        String newFileName = date + " " + fileName;
        File newFile = new File(file.getParent(), newFileName);
        boolean isRenamed = file.renameTo(newFile);
        if (!isRenamed) {
            throw new RuntimeException("Could not rename file to " + newFileName);
        }
        return newFile;
    }
}
