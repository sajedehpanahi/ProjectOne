package com.company;

import java.math.BigDecimal;

/**
 * @author Sajedeh Panahi
 */
public class ShortTerm extends DepositType {

    @Override
    public BigDecimal getInterestRate() {
        return new BigDecimal("10");
    }
}

