package org.sienkart;

import java.util.List;
import java.util.stream.Collectors;

public class CalculatorEngine {
    private List<String> instructions;

    public void loadInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public int calculateResult() {
        if (instructions == null || instructions.isEmpty()) {
            throw new IllegalArgumentException("Instructions list is empty or null");
        }

        final int applyNumber = initialValue();
        final List<String> operationsAndValuesList = operationsWithValue();

        return executeInstructions(applyNumber, operationsAndValuesList);
    }

    private int initialValue() {
        return parseToInt(instructions.stream()
                .filter(s -> s.contains("apply")).findAny()
                .orElseThrow(() -> new IllegalArgumentException("Missing required 'apply' statement"))
                .split(" ")[1].trim());
    }

    private List<String> operationsWithValue () {
       return instructions.stream()
                .filter(str -> !str.isEmpty() && !str.isBlank())
                .filter(str -> !str.contains("apply"))
                .collect(Collectors.toList());
    }

    private int executeInstructions(final int applyNumber, final List<String> operationsAndValuesList) {
        int result = applyNumber;

        for (String instruction : operationsAndValuesList) {
            String[] operationAndValue = instruction.split(" ");
            String operation = operationAndValue[0].trim();
            int value = parseToInt(operationAndValue[1].trim());
            result = executeInstruction(result, value, operation);
        }

        return result;
    }

    private int executeInstruction(final int currentResult, final int value, final String operation) {
        switch (operation) {
            case "add":
                return currentResult + value;
            case "subtract":
                return currentResult - value;
            case "multiply":
                return currentResult * value;
            case "divide":
                if (value == 0) throw new ArithmeticException("Division by zero is not allowed.");
                return currentResult / value;
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }

    private int parseToInt(final String number) {
        if (!number.matches("\\d+")) {
            throw new NumberFormatException("Value is not numeric: " + number);
        }
        return Integer.parseInt(number);
    }
}
