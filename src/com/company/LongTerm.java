package com.company;

import java.math.BigDecimal;

/**
 * <h1>Long Term Deposit Class</h1>
 * This class models long term deposit type
 * with 20% interest rate!
 *
 * @author Sajedeh Panahi
 * @version 1.0
 * @since 4/6/2016.
 */
public class LongTerm extends Account {
    /**
     *
     * @return BigDecimal This returns long term deposit type interest rate
     */
    @Override
    public BigDecimal getInterestRate(){
        return new BigDecimal("20");
    }
}
