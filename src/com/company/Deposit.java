package com.company;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <h1>Abstract Account Class</h1>
 *
 * @author Sajedeh Panahi
 * @version 1.0
 * @since 4/6/2016.
 */
public abstract class Deposit implements Comparable<Deposit> {

    private int customerNumber;
    private int durationDays;
    private BigDecimal depositBalance;

    static Deposit createDeposit(String depositType, int customerNumber, int durationDays, BigDecimal depositBalance) throws ClassNotFoundException, InstantiationException, IllegalAccessException, ArgumentOutOfRange, DurationDaysOutOfRange {
        Class<?> cls = Class.forName("com.company." + depositType);
        Deposit deposit = (Deposit)cls.newInstance();

        deposit.setCustomerNumber(customerNumber);
        deposit.setDepositBalance(depositBalance);
        deposit.setDurationDays(durationDays);
        return deposit;
    }

    public int getCustomerNumber(){
        return customerNumber;
    }

    public void setCustomerNumber(int number){
        customerNumber=number;
    }

    public int getDurationDays(){
        return durationDays;
    }

    /**
     *
     * @param durationDays is duration  days in int that must be greater than 0
     * @throws ArgumentOutOfRange on input error
     */
    public void setDurationDays(int durationDays) throws DurationDaysOutOfRange {

        if(durationDays > 0)
            this.durationDays = durationDays;
        else
            throw new DurationDaysOutOfRange("Duration Days must be greater than zero!");
    }

    /**
     *
     * @return deposit balance in BigDecimal( to handle very huge balance)
     */
    public BigDecimal getDepositBalance(){
        return depositBalance;
    }

    /**
     * @param balance deposit balance to set
     * @throws ArgumentOutOfRange Deposit Balance must be positive
     */
    public void setDepositBalance(BigDecimal balance) throws ArgumentOutOfRange {

        if (balance.compareTo(new BigDecimal(0)) < 0){
            throw new ArgumentOutOfRange("Deposit Balance must be positive!");
        }
        this.depositBalance = balance;
    }


    public Deposit(){
    }

    /**
     *Rounding mode to round towards the "nearest neighbor" unless both neighbors are equidistant,
     *  in which case, round towards the even neighbor
     * @return calculated profit in BigDecimal
     */
    public BigDecimal calculateProfit(){

        BigDecimal iR = getInterestRate();
        BigDecimal dD = new BigDecimal(durationDays);

        return ((iR.multiply(dD)).multiply(depositBalance)).divide(new BigDecimal(36500) , RoundingMode.HALF_EVEN);
    }

    public int compareTo(Deposit deposit) {
        return calculateProfit().compareTo((deposit.calculateProfit()));
    }

    public abstract BigDecimal getInterestRate();
}

