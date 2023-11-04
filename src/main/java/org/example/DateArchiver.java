package org.example;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateArchiver {
    String archivePath;
    public DateArchiver(String archivePath) {
        this.archivePath = archivePath;
    }

    /**
     * Check if archive year exists
     */
    public boolean doesArchiveYearExist(String year) {
        // check that string is only a year string
        if (this.isYearFormat(year)) {
            return false;
        }
        File archiveYearDirectory = new File(year);
        return archiveYearDirectory.exists();
    }

    /**
     * Create Archive year
     */
    public void createArchiveYear(String year) {
        // check that string is only a year string
        if (this.isYearFormat(year)) {
            throw new RuntimeException("Archive year path is not a year string");
        }
        File archiveYearDirectory = new File(this.archivePath, year);
        boolean isArchiveYearDirectoryCreated = archiveYearDirectory.mkdir();
        if (!isArchiveYearDirectoryCreated) {
            throw new RuntimeException("Could not create archive year directory " + year);
        }
    }

    /**
     * Check if input string is a year format
     * @param inputStr - the input string
     * @return true if the input string is a year format, false otherwise
     */
    private boolean isYearFormat(String inputStr) {
        // Define a regex pattern to match a 4-digit number
        String regex = "^[0-9]{4}$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object and apply the pattern to the input string
        Matcher matcher = pattern.matcher(inputStr);

        // Check if the input string matches the regex pattern
        if (!matcher.matches()) {
            return false;
        }

        // Check if the integer value of the string is within a reasonable year range
        int year = Integer.parseInt(inputStr);
        return year >= 1900 && year <= 9999;
    }
}
