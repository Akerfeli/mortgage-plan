package com.github.akerfeli.mortgageplanbackend.dto;

public class ProspectDTO {
    private String name;
    private double totalLoan;
    private double interest;
    private int years;
    private double monthlyPayment; // calculated value

    public ProspectDTO(String name, double totalLoan, double interest, int years, double monthlyPayment) {
        this.name = name;
        this.totalLoan = totalLoan;
        this.interest = interest;
        this.years = years;
        this.monthlyPayment = monthlyPayment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalLoan() {
        return totalLoan;
    }

    public void setTotalLoan(double totalLoan) {
        this.totalLoan = totalLoan;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}