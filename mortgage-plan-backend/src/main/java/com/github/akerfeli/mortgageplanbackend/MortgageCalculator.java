package com.github.akerfeli.mortgageplanbackend;

public class MortgageCalculator {

    /**
     * Rounds a given decimal number to the nearest integer using a custom rounding method.
     * This method adds 0.5 to the number for positive values and subtracts 0.5 for negative values,
     * then casts the result to an integer to achieve rounding.
     *
     * @param num The number to be rounded.
     * @return The rounded integer value.
     */
    public static int customRound(double num) {
        return (int)(num < 0 ? (num - 0.5) : (num + 0.5));
    }

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
     * Calculates the monthly loan payment in cents based on the total loan amount, interest rate, and loan duration.
     * Formula used:
     * E = Fixed monthly payment
     * b = Interest on a monthly basis
     * U = Total loan
     * p = Number of payments
     * E = U[b(1 + b)^p]/[(1 + b)^p - 1]
     *
     * @param totalLoanCents    The total loan amount.
     * @param interestRateBps The annual interest rate in percent * 100.
     * @param years        The number of years for the loan.
     * @return The monthly payment amount.
     */
    public static int calculateMonthlyPayment(long totalLoanCents, int interestRateBps, int years) {

        double monthlyInterestRate = interestRateBps / 100.0 / 100.0 / 12.0;
        int numberOfPayments = years * 12;

        double monthlyPayment =
                totalLoanCents * (monthlyInterestRate * positivePow(1+monthlyInterestRate, numberOfPayments))
                        / (positivePow(1+monthlyInterestRate, numberOfPayments) - 1);

        return customRound(monthlyPayment);
    }

}
