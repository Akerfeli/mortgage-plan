package com.github.akerfeli.mortgageplanbackend.model;

public class Prospect {
    /* Given that this project restricts the use of the Math library, the decision to use long as the
    representation for monetary values was made to prevent rounding errors that can occur with floats and doubles.
    If there were no such restrictions, BigDecimal from the Math library would have been a suitable choice. */

    private String name;
    private long totalLoanCents; // Total loan amount in cents
    private int interestRateBps; // Interest base point (0.01% -> 1)
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

    public void setTotalLoan(long totalLoanCents) {
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