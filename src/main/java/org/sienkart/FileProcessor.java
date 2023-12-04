package org.sienkart;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileProcessor {

    List<String> readInstructionsFromFile(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath));
    }

    public void processInstructionsAndCalculateResult(String filePath) {
        final CalculatorEngine calculatorEngine = new CalculatorEngine();
        try {
            final List<String> instructions = readInstructionsFromFile(filePath+".txt");
            calculatorEngine.loadInstructions(instructions);
            int finalResult = calculatorEngine.calculateResult();
            System.out.println("Result for your instructions: " + finalResult);
        } catch (IOException e) {
            System.err.println("There is no file to process " + filePath);
        } catch (IllegalArgumentException | ArithmeticException e) {
            System.err.println("Error occurred while processing instructions: " + e.getMessage());
        }
    }
}
