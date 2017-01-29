package com.mymoneycontrol.android.addmoney;

import java.util.HashMap;

/**
 * Created by jrizvan on 1/28/17.
 */

public class DailyEntryModel {

    public static final Integer one = 10;
    public static final Integer two = 20;
    public static final Integer three = 50;
    public static final Integer four = 100;
    public static final Integer five = 500;
    public static final Integer SIX = 2000;
    public static final Integer total = 999;
    private static HashMap<Integer, Double> curInputMap = new HashMap<Integer, Double>();
    Integer curButtonActionUp = 1;
    Integer curButtonActionDown = 2;
    Integer curTotal;
    DailyEntryCalculate dailyEntryCalculate = new DailyEntryCalculate();

    public DailyEntryModel() {
        curInputMap.put(one, 0.0);
        curInputMap.put(two, 0.0);
        curInputMap.put(three, 0.0);
        curInputMap.put(four, 0.0);
        curInputMap.put(five, 0.0);
        curInputMap.put(SIX, 0.0);
        curInputMap.put(total, 0.0);
    }

    public void setValue(Integer action, Integer amountType) {
        if (action == 1) {
            curInputMap.put(amountType, curInputMap.get(amountType) + 1);
        } else {
            curInputMap.put(amountType, curInputMap.get(amountType) - 1);
        }

        //Total
        curInputMap.put(total, dailyEntryCalculate.calculate(action, amountType, curInputMap.get(total)));
    }

    public HashMap<Integer, Double> getCurInputMap() {
        return curInputMap;
    }
}
