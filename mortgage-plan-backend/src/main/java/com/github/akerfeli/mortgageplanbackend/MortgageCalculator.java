package com.github.akerfeli.mortgageplanbackend;

public class MortgageCalculator {

    /**
     * Calculates the power of a given base to a positive exponent.
     * This method is implemented due to the restriction on using the java.math library and
     * similar math dependencies. This method assumes that the exponent is always positive.
     *
     * @param base     The base value.
     * @param exponent The positive exponent.
     * @return The result of raising the base to the positive exponent.
     */
    public static double positivePow(double base, int exponent) {

        if (base == 0) return 0;

        if (exponent <= 0) {
            throw new IllegalArgumentException("Exponent must be a positive integer");
        }

        double result = 1;

        for (int i = 1; i <= exponent; i++) {
            result *= base;
        }

        return result;
    }

    /**
     * Calculates the monthly loan payment based on the total loan amount, interest rate, and loan duration.
     * Formula used:
     * E = Fixed monthly payment
     * b = Interest on a monthly basis
     * U = Total loan
     * p = Number of payments
     * E = U[b(1 + b)^p]/[(1 + b)^p - 1]
     *
     * @param totalLoan    The total loan amount.
     * @param interestRate The annual interest rate.
     * @param years        The number of years for the loan.
     * @return The monthly payment amount.
     */
    public static double calculateMonthlyPayment(double totalLoan, double interestRate, int years) {

        double monthlyInterestRate = interestRate / 100.0 / 12.0;
        int numberOfPayments = years * 12;

        double monthlyPayment =
                totalLoan * (monthlyInterestRate * positivePow(1+monthlyInterestRate, numberOfPayments))
                        / (positivePow(1+monthlyInterestRate, numberOfPayments) - 1);

        return monthlyPayment;
    }

}
