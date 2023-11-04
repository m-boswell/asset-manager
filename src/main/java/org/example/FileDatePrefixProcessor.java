package org.example;

import java.io.File;
import java.util.Date;

public class FileDatePrefixProcessor {
    private final DatePrefix datePrefix;
    public FileDatePrefixProcessor() {
        // Define a regular expression pattern for ISO 8601 timestamp prefix. Instead of colons this ISO 8601 variant uses dots.
        String isoTimestampPatternForRegexCheck = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}\\.\\d{2}\\.\\d{2}(\\.\\d{3})?";
        datePrefix = new DatePrefix("yyyy-MM-dd'T'HH.mm.ss", isoTimestampPatternForRegexCheck, " ");
    }

    /**
     * Process an asset and prefix the file name with the last modified date if it does not already have a date or time prefix. If the file name contains a date expression, prefix the file name with the date expression instead.
     * @param asset - the asset to process
     * @return the processed asset
     */
    public Asset process(Asset asset) {
        File file = Asset.toFile(asset);
        String fileName = file.getName();
        if (datePrefix.isStringPrefixed(fileName)) {
            return asset;
        }
        Date date = new Date(file.lastModified());
        File prefixedFile = this.prefixFileNameWithDate(file, date);
        return Asset.fromFile(prefixedFile);
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
        String newFileName = this.getFileNameWithPrefixedDate(file, date);
        return this.renameFile(file, newFileName);
    }

    /**
     * Get the file name with a prefixed date
     * @param file - the file to prefix
     * @param date - the date to prefix the file name with
     * @return the prefixed file name
     */
    private String getFileNameWithPrefixedDate(File file, Date date) {
        return datePrefix.prefixStringWithDateFormat(file.getName(), date);
    }

    /**
     * Rename a file
     * @param file - the file to rename
     * @param newFileName  - the new file name
     * @return the renamed file
     */
    private File renameFile(File file, String newFileName) {
        File newFile = new File(file.getParent(), newFileName);
        boolean isRenamed = file.renameTo(newFile);
        if (!isRenamed) {
            throw new RuntimeException("Could not rename file to " + newFileName);
        }
        return newFile;
    }

//    /**
//     * Process an asset file after prompting the user and obtaining consent.
//     * @param asset - the asset to process
//     * @return the processed asset if the user accepts, or the original asset otherwise
//     * @throws ParseException - if the date cannot be parsed
//     */
//    public Asset processWithPrompt(Asset asset) throws ParseException {
//        File file = asset.getFileFromPath();
//
//        // Describe what will happen
//        String message = getProcessingMessage(file);
//        System.out.println(message);
//
//        boolean isConsent = getUserConsent();
//
//        if (isConsent) {
//            return process(asset); // Process the file if user agrees
//        } else {
//            System.out.println("No changes were made to the file.");
//            return asset; // Return the original asset if user disagrees
//        }
//    }
//
//    private boolean getUserConsent() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Do you want to proceed with these changes? (yes/no): ");
//        String userInput = scanner.nextLine();
//        return "yes".equalsIgnoreCase(userInput);
//    }
//
//    /**
//     * Constructs a message describing what will happen to the file.
//     * @param file - the file to be processed
//     * @return a String message
//     */
//    private String getProcessingMessage(File file) throws ParseException {
//        String fileName = file.getName();
//        Date date;
//
//        if (datePrefix.isStringPrefixed(fileName)) {
//            return "The file '" + fileName + "' will not be modified.";
//        } else if (Utils.isStringContainingDateExpression(fileName)) {
//            date = Utils.convertStringToDate(fileName);
//        } else {
//            date = new Date(file.lastModified());
//        }
//
//        return "The file '" + fileName + "' will be prefixed with the date: " + DatePrefix.format(date) + "so the new file name will be: " + date + " " + fileName;
//    }


}
