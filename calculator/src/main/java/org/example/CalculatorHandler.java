package org.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * An invocation handler that logs method calls and delegates them to an original instance of a calculator.
 */
public class CalculatorHandler implements InvocationHandler {

    private final Calculator original;

    /**
     * Creates a new calculator handler.
     *
     * @param original the original calculator instance
     */
    public CalculatorHandler(Calculator original) {
        this.original = original;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Build the argument list
        var builder = new StringBuilder();
        builder.append("(");
        for (int i = 0; i < args.length; i++) {
            builder.append(args[i]);
            if (i < args.length - 1) {
                builder.append(", ");
            }
        }
        builder.append(")");

        // Log method call
        System.out.println("Invoking method: " + method.getName() + builder);

        // Invoke the original method on the original instance
        return method.invoke(original, args);
    }

    /**
     * Creates a proxy instance of a calculator.
     *
     * @param original the original calculator instance
     * @return the proxy instance
     */
    public static Calculator createProxy(Calculator original) {
        return (Calculator) Proxy.newProxyInstance(
                Calculator.class.getClassLoader(),
                new Class[]{Calculator.class},
                new CalculatorHandler(original)
        );
    }

    public static void main(String[] args) {
        // Create an original calculator instance
        Calculator originalCalculator = new BasicCalculator();

        // Create a proxy instance
        Calculator proxyCalculator = createProxy(originalCalculator);

        // Use the proxy instance
        System.out.println("Result of addition: " + proxyCalculator.add(1, 2));
        System.out.println("Result of subtraction: " + proxyCalculator.subtract(4, 2));
    }
}
