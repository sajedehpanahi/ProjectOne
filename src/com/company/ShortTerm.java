package com.company;

import java.math.BigDecimal;

/**
 * <h1>Short Term Deposit Class</h1>
 * This class models short term deposit type
 * with 10% interest rate!
 *
 * @author Sajedeh Panahi
 * @version 1.0
 * @since 4/6/2016.
 */
public class ShortTerm extends Account{
    /**
     *
     * @return BigDecimal This returns short term deposit type interest rate
     */
    @Override
    public BigDecimal getInterestRate(){
        return new BigDecimal("10");
    }
}

