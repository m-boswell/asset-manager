public class FileDateProcessorTest {

//    @Test
//    public void testProcessWithBlankPath() {
//        FileProcessor fileProcessor = new FileProcessor();
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            fileProcessor.process("");
//        });
//    }
//
//    @Test
//    public void testProcessWithNonExistentFile() {
//        FileProcessor fileProcessor = new FileProcessor();
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            fileProcessor.process("nonexistentfile.txt");
//        });
//    }
//
//    @Test
//    public void testProcessWithDirectory() throws IOException {
//        Path tempDir = Files.createTempDirectory("test");
//        FileProcessor fileProcessor = new FileProcessor();
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            fileProcessor.process(tempDir.toString());
//        });
//        Files.delete(tempDir);
//    }
//
//    @Test
//    public void tesThatResourceExists() throws IOException, URISyntaxException {
//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//        File file = new File(classLoader.getResource("sample-video.mp4").getFile());
//        String content = new String(Files.readAllBytes(file.toPath()));
////        if (resource == null) {
////            throw new IllegalArgumentException("file not found!");
////        } else {
////
////            // failed if files have whitespaces or special characters
////            //return new File(resource.getFile());
////
////            return ;
////        }
//
//        // Use getResourceAsStream to get the resource
////        var resourceAsStream = getClass().getResourceAsStream("/sample-video.mp4");
//        // Assert that the resource exists
//        Assertions.assertNotNull(content);
//
//    }
//
////    @Test
////    public void testProcessWithUnreadableFile() throws IOException {
////        Path tempFile = Files.createTempFile("testNotReadable", ".txt");
////        File file = tempFile.toFile();
////        file.setReadable(false);
////        FileProcessor fileProcessor = new FileProcessor();
////        Assertions.assertThrows(IllegalArgumentException.class, () -> {
////            fileProcessor.process(file.getAbsolutePath());
////        });
////        Files.delete(tempFile);
////    }
//
////    @Test
////    public void testProcessWithUnwritableFile() throws IOException {
////        Path tempFile = Files.createTempFile("testNotWritable", ".txt");
////        File file = tempFile.toFile();
////        file.setWritable(false);
////        FileProcessor fileProcessor = new FileProcessor();
////        Assertions.assertThrows(IllegalArgumentException.class, () -> {
////            fileProcessor.process(file.getAbsolutePath());
////        });
////        Files.delete(tempFile);
////    }
//
//    @Test
//    public void testProcessWithFileWithoutDateOrTimePrefix() throws IOException {
//        File tempFile = File.createTempFile("testPrefixMe", ".txt");
//        String path = tempFile.toString();
//        FileProcessor fileProcessor = new FileProcessor();
//        fileProcessor.process(path);
//        String fileName = tempFile.getName();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH.mm.ss");
//        LocalDateTime now = LocalDateTime.now();
//        String expectedFileName = now.format(formatter) + " " + fileName;
//        File expectedFile = new File(tempFile.getParent(), expectedFileName);
//
//        Assertions.assertTrue(expectedFile.exists());
//        Assertions.assertFalse(tempFile.exists());
//        tempFile.delete();
//        expectedFile.delete();
//    }
//
//    @Test
//    public void testProcessWithFileWithDateOrTimePrefix() throws IOException {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH.mm.ss");
//        LocalDateTime now = LocalDateTime.now();
//        String prefix = now.format(formatter) + " ";
//        File tempFileWithPrefix = File.createTempFile(prefix, ".txt");
//        String expectedFileName = tempFileWithPrefix.getName();
//
//        FileProcessor fileProcessor = new FileProcessor();
//        fileProcessor.process(tempFileWithPrefix.toString());
//
//        Assertions.assertEquals(tempFileWithPrefix.getName(), expectedFileName);
//
//        tempFileWithPrefix.delete();
//    }
//
////    @Test
////    public void testGetDestinationPath() {
////        FileProcessor fileDateProcessor = new FileProcessor();
////        Assertions.assertNull(fileDateProcessor.getDestinationPath());
////    }
////
////    @Test
////    public void testGetDestinationPathWithDestinationPathSet() {
////        FileProcessor fileDateProcessor = new FileProcessor();
////        fileDateProcessor.setDestinationPath("test");
////        Assertions.assertEquals("test", fileDateProcessor.getDestinationPath());
////    }
////
////    // unit tests for wrapFileInDirectory
////    @Test
////    public void testWrapFileInDirectory() {
////        FileProcessor fileDateProcessor = new FileProcessor();
////        File file = new File("test.txt");
////        File directory = new File("test");
////        File wrappedFile = fileDateProcessor.wrapFileInDirectory(file, directory);
////        Assertions.assertEquals("test/test.txt", wrappedFile.getPath());
////    }

}