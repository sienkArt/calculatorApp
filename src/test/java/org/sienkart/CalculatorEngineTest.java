package org.sienkart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorEngineTest {

    private static List<Object[]> testData() {
        return Arrays.asList(
                new Object[]{"add 2", "multiply 3", "apply 10", 36},
                new Object[]{"multiply 3", "add 2", "apply 10", 32},
                new Object[]{"multiply 2", "multiply 2", "apply 5", 20},
                new Object[]{"multiply 10", "multiply 10", "apply 100", 10000},
                new Object[]{"multiply 10", "    ", "apply 100", 1000},
                new Object[]{"multiply 10", "", "apply 100", 1000},
                new Object[]{"add 2", null, "apply 10", 12},
                new Object[]{"apply 1", null, null, 1}
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testCalculateResult(final String instruction1, final String instruction2, final String instruction3, int expected) {
        final CalculatorEngine calculatorEngine = new CalculatorEngine();
        final List<String> instructions = new ArrayList<>();
        if (instruction1 != null) instructions.add(instruction1);
        if (instruction2 != null) instructions.add(instruction2);
        if (instruction3 != null) instructions.add(instruction3);

        calculatorEngine.loadInstructions(instructions);
        int result = calculatorEngine.calculateResult();
        assertEquals(expected, result);
    }

    @Test
    public void testCalculateResultWithNoInstructions() {
        final CalculatorEngine calculatorEngine = new CalculatorEngine();
        calculatorEngine.loadInstructions(new ArrayList<>());
        Throwable exception = assertThrows(IllegalArgumentException.class, calculatorEngine::calculateResult);
        final String expectedMessage = "Instructions list is empty or null";
        assertTrue(exception.getMessage().equals(expectedMessage));
    }

    @Test
    public void testCalculateResultWithoutApply() {
        final CalculatorEngine calculatorEngine = new CalculatorEngine();
        calculatorEngine.loadInstructions(Arrays.asList("add 1"));
        Throwable exception = assertThrows(IllegalArgumentException.class, calculatorEngine::calculateResult);
        final String expectedMessage = "Missing required 'apply' statement";
        assertTrue(exception.getMessage().equals(expectedMessage));
    }

}
