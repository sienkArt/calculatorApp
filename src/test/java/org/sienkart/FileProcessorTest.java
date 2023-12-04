package org.sienkart;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileProcessorTest {

    @Test
    public void testReadInstructionsFromFile() throws IOException {
        String filePath = "test_instructions.txt";
        String content = "add 5\nmultiply 3\napply 10";
        Path pathOfFile = Path.of(filePath);
        Files.write(pathOfFile, content.getBytes());

        FileProcessor fileProcessor = new FileProcessor();
        List<String> instructions = fileProcessor.readInstructionsFromFile(filePath);

        assertEquals(3, instructions.size());
        assertEquals("add 5", instructions.get(0));
        assertEquals("multiply 3", instructions.get(1));
        assertEquals("apply 10", instructions.get(2));

        assertThrows(IOException.class, () -> fileProcessor.readInstructionsFromFile("non_existing_file.txt"));

        Files.deleteIfExists(pathOfFile);
    }
}
