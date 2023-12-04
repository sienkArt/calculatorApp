package org.sienkart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InputValidatorTest {

    final static String ERROR_MESSAGE = "Usage: java -jar calculator.jar <filePath>";

    @Test
    public void testValidateArguments() {
        String[] validArgs = {"instructions.txt"};
        assertEquals("instructions.txt", InputValidator.validateArguments(validArgs));

        String[] emptyArgs = {};
        assertEquals(ERROR_MESSAGE, InputValidator.validateArguments(emptyArgs));

        String[] multipleArgs = {"file1.txt", "file2.txt"};
        assertEquals(ERROR_MESSAGE, InputValidator.validateArguments(multipleArgs));
    }
}
