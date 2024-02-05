package com.github.akerfeli.mortgageplanbackend.dto;

public class ProspectDTO {
    private String name;
    private long totalLoanCents;
    private int interestRateBsp;
    private int years;
    private int monthlyPayment; // calculated value

    public ProspectDTO(String name, long totalLoanCents, int interestRateBsp, int years, int monthlyPayment) {
        this.name = name;
        this.totalLoanCents = totalLoanCents;
        this.interestRateBsp = interestRateBsp;
        this.years = years;
        this.monthlyPayment = monthlyPayment;
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

    public int getInterestRateBsp() {
        return interestRateBsp;
    }

    public void setInterestRateBsp(int interestRateBsp) {
        this.interestRateBsp = interestRateBsp;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(int monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}