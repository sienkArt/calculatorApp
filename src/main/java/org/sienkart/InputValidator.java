package org.sienkart;

public class InputValidator {
    public static String validateArguments(final String[] args) {
        if (args.length != 1) {
            return "Usage: java -jar calculator.jar <filePath>";
        }
        return args[0];
    }
}

