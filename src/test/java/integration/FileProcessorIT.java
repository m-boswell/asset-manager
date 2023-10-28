package integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

public class FileProcessorIT {
    @Test
    public void testThatResourceExists() throws IOException, URISyntaxException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource("saample-video.mp4").getFile());
        String content = new String(Files.readAllBytes(file.toPath()));
//        if (resource == null) {
//            throw new IllegalArgumentException("file not found!");
//        } else {
//
//            // failed if files have whitespaces or special characters
//            //return new File(resource.getFile());
//
//            return ;
//        }

        // Use getResourceAsStream to get the resource
//        var resourceAsStream = getClass().getResourceAsStream("/sample-video.mp4");
        // Assert that the resource exists
        Assertions.assertNotNull(content);

    }
}
