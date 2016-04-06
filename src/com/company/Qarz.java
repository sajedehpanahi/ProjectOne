package com.company;

import java.math.BigDecimal;

/**
 * Created by DotinSchool2 on 4/6/2016.
 */
public class Qarz extends Account {

    @Override
    public BigDecimal getInterestRate(){
        return new BigDecimal("0");
    }
}
