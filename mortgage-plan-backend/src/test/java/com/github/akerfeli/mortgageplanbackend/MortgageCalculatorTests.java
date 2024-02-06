package com.github.akerfeli.mortgageplanbackend;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MortgageCalculatorTests {

    @Test
    public void testCalculateMonthlyPayment() {
        assertEquals(53682, MortgageCalculator.calculateMonthlyPayment(10000000, 500, 30));
        assertEquals(888, MortgageCalculator.calculateMonthlyPayment(10000, 1200, 1));
        assertEquals(188294, MortgageCalculator.calculateMonthlyPayment(4000000, 1200, 2));
    }
}