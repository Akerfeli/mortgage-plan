package com.github.akerfeli.mortgageplanbackend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomRoundTests {

    @Test
    public void testCustomRound() {
        // Test rounding a positive decimal number
        assertEquals(5, MortgageCalculator.customRound(4.6));

        // Test rounding a negative decimal number
        assertEquals(-4, MortgageCalculator.customRound(-3.7));

        // Test rounding zero
        assertEquals(0, MortgageCalculator.customRound(0));

        // Test rounding a decimal number with .5
        assertEquals(3, MortgageCalculator.customRound(2.5));

    }
}
