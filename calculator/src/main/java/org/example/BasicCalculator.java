package org.example;

/**
 * A basic calculator.
 */
public class BasicCalculator implements Calculator {

    /**
     * {@inheritDoc}
     */
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int subtract(int a, int b) {
        return a - b;
    }
}
