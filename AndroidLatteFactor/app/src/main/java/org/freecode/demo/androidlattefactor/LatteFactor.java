package org.freecode.demo.androidlattefactor;

import java.text.DecimalFormat;

/**
 * Created by jonliu on 2/24/2019.
 */

public class LatteFactor {
    private double balance = 0.0d;
    private double growthRate = 0.0d;
    private double regularContribution = 0.0d;

    public LatteFactor(double initialValue, double growthRate) {
        this.balance = initialValue;
        this.growthRate = growthRate;
    }

    public LatteFactor(double initialValue, double regularContribution, double growthRate) {
        this.balance = initialValue;
        this.growthRate = growthRate;
        this.regularContribution = regularContribution;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(double growthRate) {
        this.growthRate = growthRate;
    }

    public double getRegularContribution() {
        return regularContribution;
    }

    public void setRegularContribution(double regularContribution) {
        this.regularContribution = regularContribution;
    }

    public static String formatDecimalValue(double value) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        return df.format(value);
    }

    public double calculate(int times) {
        if (times > 0) {
            this.balance = this.balance * (1 + this.growthRate) + this.regularContribution;
            calculate(--times);
        }
        return getBalance();
    }
}
