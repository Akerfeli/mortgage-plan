package com.github.akerfeli.mortgageplanbackend;

import org.junit.jupiter.api.Test;

import static com.github.akerfeli.mortgageplanbackend.MortgageCalculator.positivePow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PositivePowTests {

    @Test
    public void testPositivePow() {
        // Test case: 2^3
        assertEquals(8, positivePow(2, 3), 0.0001);

        // Test case: 5^4
        assertEquals(625, positivePow(5, 4), 0.0001);

        // Test case: 1.5^4
        assertEquals(5.0625, positivePow(1.5, 4), 0.0001);

        // Test case: 2.33^10
        assertEquals(4715.84161164, positivePow(2.33, 10), 0.0001);
    }

    @Test
    public void testNegativeExponent() {
        // Test case with negative exponent
        assertThrows(IllegalArgumentException.class, () -> positivePow(3, -2));
    }

    @Test
    public void testZeroExponent() {
        // Test case with zero as exponent
        assertThrows(IllegalArgumentException.class, () -> positivePow(4, 0));
    }
}