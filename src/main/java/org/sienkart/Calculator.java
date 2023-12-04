package org.sienkart;

public class Calculator {
    public void calculate(String[] args) {
        final String userArgument = InputValidator.validateArguments(args);

        final FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.processInstructionsAndCalculateResult(userArgument);
    }
}

