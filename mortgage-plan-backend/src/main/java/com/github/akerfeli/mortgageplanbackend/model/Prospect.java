package com.github.akerfeli.mortgageplanbackend.model;

import jakarta.validation.constraints.*;

public class Prospect {
    /* Given that this project restricts the use of the Math library, the decision to use long as the
    representation for monetary values was made to prevent rounding errors that can occur with floats and doubles.
    If there were no such restrictions, BigDecimal from the Math library would have been a suitable choice. */

    @NotEmpty(message = "Name must not be empty")
    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;
    @Positive(message = "Total loan must be positive")
    private long totalLoanCents; // Total loan amount in cents
    @Positive(message = "Interest rate must be positive")
    @Max(value = 10000, message = "Interest rate can't be over 100%")
    private int interestRateBps; // Interest base point (0.01% -> 1)
    @Positive(message = "Years must be positive")
    private int years;

    public Prospect(String name, long totalLoanCents, int interestRateBps, int years) {
        this.name = name;
        this.totalLoanCents = totalLoanCents;
        this.interestRateBps = interestRateBps;
        this.years = years;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotalLoanCents() {
        return totalLoanCents;
    }

    public void setTotalLoanCents(long totalLoanCents) {
        this.totalLoanCents = totalLoanCents;
    }

    public int getInterestRateBps() {
        return interestRateBps;
    }

    public void setInterestRateBps(int interestRateBps) {
        this.interestRateBps = interestRateBps;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }
}