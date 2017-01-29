package com.mymoneycontrol.android.addmoney;

/**
 * Created by jrizvan on 1/28/17.
 */

public class DailyEntryCalculate {


    public DailyEntryCalculate() {
    }

    public Double calculate(Integer type, Integer amountType, Double amount) {
        //add
        if (type == 1) {
            return amountType + amount;
        }

        if (amount > amountType)
            return amount - amountType;
        else
            return amountType - amount;
    }
}
