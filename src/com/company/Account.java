package com.company;
import java.math.BigDecimal;

/**
 * Created by DotinSchool2 on 4/6/2016.
 */
public abstract class Account {

    private int customerNumber;
    protected int durationDays;
    protected BigDecimal depositBalance;
    protected BigDecimal profit;
    private String depositType;
    protected String errorMessage;

    public Account(){
    }

    public Account(int customerNumber,int durationDays,BigDecimal depositBalance, String depositType){
        this.customerNumber=customerNumber;
        this.durationDays=durationDays;
        this.depositBalance=depositBalance;
        this.depositType=depositType;
    }

    public BigDecimal calulateDeposit(){

        BigDecimal iR = getInterestRate();
        BigDecimal dD = new BigDecimal(durationDays);
        profit=((iR.multiply(dD)).multiply(depositBalance)).divide(new BigDecimal(36500));

        return profit;
    }

    public abstract BigDecimal getInterestRate();
}

