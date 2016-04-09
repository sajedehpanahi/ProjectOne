package com.company;

import java.math.BigDecimal;

/**
 * Created by DotinSchool2 on 4/9/2016.
 */
public abstract class DepositType {

    private BigDecimal InterestRate = getInterestRate();

    public abstract BigDecimal getInterestRate();


}
