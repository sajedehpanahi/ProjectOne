package com.company;

import Exceptions.ArgumentOutOfRangeException;
import Exceptions.DurationDaysOutOfRangeException;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Sajedeh Panahi
 */

public class Deposit implements Comparable<Deposit> {

    private String customerNumber;
    private int durationDays;
    private BigDecimal depositBalance;
    private BigDecimal payedInterest;
    private BigDecimal interestRate;

    public Deposit(String depositType, String customerNumber, int durationDays, BigDecimal depositBalance) throws ClassNotFoundException, InstantiationException, IllegalAccessException, ArgumentOutOfRangeException, DurationDaysOutOfRangeException {

        setCustomerNumber(customerNumber);
        setDepositBalance(depositBalance);
        setDurationDays(durationDays);
        setInterestRate(depositType);

        calculateInterest();
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(int durationDays) throws DurationDaysOutOfRangeException {

        if (durationDays > 0)
            this.durationDays = durationDays;
        else
            throw new DurationDaysOutOfRangeException("Duration Days must be greater than zero!");
    }

    public BigDecimal getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(BigDecimal balance) throws ArgumentOutOfRangeException {

        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new ArgumentOutOfRangeException("Deposit Balance must be positive!");
        }
        this.depositBalance = balance;
    }

    public BigDecimal getPayedInterest() {
        return payedInterest;
    }

    public void setPayedInterest(BigDecimal payedInterest) {

        this.payedInterest = payedInterest;
    }

    public void setInterestRate(String depositType) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName("com.company." + depositType);
        DepositType depositTypeClass = (DepositType) clazz.newInstance();

        this.interestRate = depositTypeClass.getInterestRate();
    }

    public void calculateInterest() {

        BigDecimal durationDays = new BigDecimal(this.durationDays);
        payedInterest = ((interestRate.multiply(durationDays)).multiply(depositBalance)).divide(new BigDecimal(36500), RoundingMode.HALF_EVEN);
    }

    public int compareTo(Deposit deposit) {
        return payedInterest.compareTo((deposit.payedInterest));
    }
}

