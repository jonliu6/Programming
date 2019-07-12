package org.freecode.demo;

import java.text.DecimalFormat;

public class SavingDemo {
	private double balance = 0.0d;
	private double growthRate = 0.0d;
	private double regularContribution = 0.0d;
	
	public SavingDemo(double initialValue, double growthRate) {
		this.balance = initialValue;
		this.growthRate = growthRate;
	}
	
	public SavingDemo(double initialValue, double regularContribution, double growthRate) {
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

	public static void main(String[] args) {
		SavingDemo demo = new SavingDemo(0, 96000, 0.03); // $1000 down payment, $25 monthly contribution, 0.5% average increasing rate per month = 6% annual growth
		int num = 30; // 240 months = 20 years
		System.out.println("Your balance is " + SavingDemo.formatDecimalValue(demo.calculate(num)));
	}
	
	public static String formatDecimalValue(double value) {
		DecimalFormat df = new DecimalFormat("#,###.##");
		return df.format(value);
	}
	
	public double calculate(int times) {
		if (times > 0) {
			this.balance = this.balance * (1 + this.growthRate) + this.regularContribution;
			System.out.println(times + ": " + formatDecimalValue(this.balance));
			calculate(--times);
		}
		return getBalance();
	}
}
