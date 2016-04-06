package com.company;
import java.math.BigDecimal;

/**
 * Created by DotinSchool2 on 4/6/2016.
 */
public abstract class Account {

    private int customerNumber;
    private int durationDays;
    private BigDecimal depositBalance;
    private BigDecimal profit=new BigDecimal("0");
    private String errorMessage=null;

    public int getCustomerNumber(){
        return customerNumber;
    }

    public void setCustomerNumber(int number){
        customerNumber=number;
    }

    public int getDurationDays(){
        return durationDays;
    }

    public void setDurationDays(int days){
         int flag=0;
        try{
            if(days>0)
                durationDays = days;
            else if(days == 0){
                flag=1;
                throw new Exception() ;
            }else if(days < 0){
                flag=2;
                throw new Exception() ;
            }
        }catch(Exception e){
            if(flag==1)
                errorMessage="Duration Days Cannot Be 0!";
            else if(flag == 2)
                errorMessage="Duration Days Cannot Be Negative!";
        }
    }

    public BigDecimal getDepositBalance(){
        return depositBalance;
    }

    public void setDepositBalance(BigDecimal balance) throws ArgumentOutOfRange {

        if (balance.compareTo(new BigDecimal(0)) < 0){
            throw new ArgumentOutOfRange("Deposit Balance must be positive");
        }
        this.depositBalance = balance;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public Account(){
    }

    public BigDecimal calculateProfit(){


        BigDecimal iR = getInterestRate();
        BigDecimal dD = new BigDecimal(durationDays);
        profit=((iR.multiply(dD)).multiply(depositBalance)).divide(new BigDecimal(36500) , 4);

        return profit;
    }

    public abstract BigDecimal getInterestRate();
}

