package com.company;

import java.math.BigDecimal;

/**
 * <h1>Loan Savings Deposit Class</h1>
 * This class models loan savings deposit type
 * with no interest rate!
 *
 * @author Sajedeh Panahi
 * @version 1.0
 * @since 4/6/2016.
 */
public class Qarz extends Deposit {
    /**
     *
     * @return BigDecimal This returns zero
     * */
    @Override
    public BigDecimal getInterestRate(){
        return new BigDecimal("0");
    }
}
