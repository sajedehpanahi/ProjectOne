package com.company;

import java.math.BigDecimal;

/**
 * Created by DotinSchool2 on 4/6/2016.
 */
public class LongTerm extends Account {

    @Override
    public BigDecimal getInterestRate(){
        return new BigDecimal("20");
    }
}
