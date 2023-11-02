package org.example;

import java.io.File;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // get directory path from user input which has the files we want to process.
        String directoryPath = Utils.getDirectoryPathFromUserInput();
        // process files
        List<Asset> assets = getAssetsFromUserPrompt();
    }

    // Get a list of assets from user prompt
    private static List<Asset> getAssetsFromUserPrompt() {
        // get directory path from user input which has the files we want to process.
        String directoryPath = Utils.getDirectoryPathFromUserInput();
        // get archive path from user input
        String archivePath = Utils.getArchivePathFromUserInput();
        // get files from directory
        List<File> files = Utils.getListFiles(directoryPath);
        // loop through all the files and do a file check
        Utils.checkFiles(files);
        return Asset.fromFiles(files);
    }
}