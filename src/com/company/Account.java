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
    private String depositType;
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

    public void setDepositBalance(BigDecimal balance){
        int flag=0;
        int result = balance.compareTo(new BigDecimal("0"));
        try{
            if(result == 0 || result == 1 )
                depositBalance = balance;
            else if(result == -1) {
                flag = 1;
                throw new Exception() ;
            }
        }catch(Exception e){
            if(flag==1)
                errorMessage="Deposit Balance Cannot Be Negative!";
        }
    }

    public BigDecimal getProfit(){

        return profit;
    }

    public void setProfit(BigDecimal prf){
        profit = prf;
    }

    public String getDepositType(){
        return depositType;
    }

    public void setDepositType(String type){
        type = type.toLowerCase();
        try {
            if (type == "qarz" || type == "shortterm" || type == "longterm")
                depositType = type;
            else {
                throw new Exception() ;
            }
        }catch (Exception e){
            errorMessage = "Unknown Deopsit Type!";
        }
    }

    public String getErrorMessage(){
        return errorMessage;
    }

   /* private void setErrorMessage(String msg){
        errorMessage = msg;
    }*/

    public Account(){
    }

    public Account(int customerNumber,int durationDays,BigDecimal depositBalance, String depositType){
        this.customerNumber=customerNumber;
        this.durationDays=durationDays;
        this.depositBalance=depositBalance;
        this.depositType=depositType;
    }

    public BigDecimal calulateDeposit(){
        //do something if errormessage != null

        BigDecimal iR = getInterestRate();
        BigDecimal dD = new BigDecimal(durationDays);
        profit=((iR.multiply(dD)).multiply(depositBalance)).divide(new BigDecimal(36500));

        return profit;
    }

    public abstract BigDecimal getInterestRate();
}

