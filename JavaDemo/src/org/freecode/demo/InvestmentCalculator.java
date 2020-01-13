package org.freecode.demo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This investment calculation is developed by Larry Bates, the author of "Beat the Bank"
 * Please visit https://larrybates.ca/t-rex-score/ for details.
 * To run the program, you need to provide:
 * - initial amount of the investment
 * - annual contribution - amount you may constantly add to your investment every year
 * - return rate (average return rate based on your investment. e.g. stock or bond or mutual funds)
 * - annual fee or cost (charged by financial institution or advisor)
 * - duration in years - e.g. 10 years
 * The result is presented as an object including the follows:
 * - Gross Investment Value ($)
 * - Gross Gain ($)
 * - Net Investment Value ($)
 * - Net Gain ($)
 * - Fee or Cost Paid ($)
 * - Diminish Compound Lost ($)
 * - Total Lost to Fee or Cost ($)
 * - Total Return Efficiency Index(T-REX) Score (%)
 */
public class InvestmentCalculator {
	
	public static void main(String[] args) {
		InvestmentCalculator calc = new InvestmentCalculator();
		calc.setInitialLumpsum(100);
		calc.setAnnualContribution(0);
		calc.setAnnualReturnRate(0.08);
		calc.setAnnualCostRate(0.02);
		int years = 10;
		
		System.out.println("You investment after " + years + " year(s) will be: " + calc.calculate(years, null));
		
		System.out.println("Details:\n" + calc.formatInvestmentValueDetails());
	}

	private double initialLumpsum; // in $ amount
	private double annualContribution; // in $ amount
	private double annualReturnRate; // in decimal
	private double annualCostRate; // in decimal
	
	private List<InvestmentValueObject> investmentValueDetails = null;
	
	public double getInitialLumpsum() {
		return initialLumpsum;
	}
	public void setInitialLumpsum(double initialLumpsum) {
		this.initialLumpsum = initialLumpsum;
	}
	public double getAnnualContribution() {
		return annualContribution;
	}
	public void setAnnualContribution(double annualContribution) {
		this.annualContribution = annualContribution;
	}
	public double getAnnualReturnRate() {
		return annualReturnRate;
	}
	public void setAnnualReturnRate(double annualReturnRate) {
		this.annualReturnRate = annualReturnRate;
	}
	public double getAnnualCostRate() {
		return annualCostRate;
	}
	public void setAnnualCostRate(double annualCostRate) {
		this.annualCostRate = annualCostRate;
	}	
	
	public String formatInvestmentValueDetails() {
		StringBuilder details = null;
		
		if (investmentValueDetails != null && investmentValueDetails.size() > 0) {
			details = new StringBuilder();
			for (InvestmentValueObject obj : investmentValueDetails) {
				details.append(obj.toString());
				details.append("\n");
			}
		}
		return details.toString();
	}
	
	public static String formatDecimalValue(double value) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        return df.format(value);
	}
	
	public static String formatPercentageValue(double value) {
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(value * 100);
	}
	
	public InvestmentCalculator.InvestmentValueObject calculate(int numberOfYears, InvestmentValueObject investObj) {
		if (investObj == null) {
			investObj = new InvestmentValueObject();
			double grossInvestVal = (getInitialLumpsum() + getAnnualContribution()) * (1 + getAnnualReturnRate());
			double grossGain = grossInvestVal - initialLumpsum - annualContribution * numberOfYears;
			double netInvestVal = (initialLumpsum + annualContribution) * (1 + annualReturnRate - annualCostRate);
			double netGain = netInvestVal - initialLumpsum - annualContribution * numberOfYears;
			double feePaid = (initialLumpsum + annualContribution) * annualCostRate;
			double diminishCompoundingLost = grossGain - netGain - feePaid;
			double totalLostToFee = feePaid + diminishCompoundingLost;
			double trexScore = netGain / grossGain;
			
			investObj.setGrossInvestmentValue(grossInvestVal);
			investObj.setGrossGain(grossGain);
			investObj.setNetInvestmentValue(netInvestVal);
			investObj.setNetGain(netGain);
			investObj.setFeePaid(feePaid);
			investObj.setDiminishCompoundingLost(diminishCompoundingLost);
			investObj.setTotalLostToFee(totalLostToFee);
			investObj.setTrexScore(trexScore);
			
			if (investmentValueDetails == null) {
				investmentValueDetails = new ArrayList<InvestmentValueObject>();
			}
			investmentValueDetails.add(investObj.clone(investObj)); // store investment values for each year
			
			return calculate(numberOfYears - 1, investObj);
		}
		else {
			if (numberOfYears < 1) {
				return investObj;
			}
			else {
				double grossInvestVal = (investObj.getGrossInvestmentValue() + annualContribution) * (1 + annualReturnRate);
				double grossGain = grossInvestVal - initialLumpsum - annualContribution * numberOfYears;
				double netInvestVal = (investObj.getNetInvestmentValue() + annualContribution) * (1 + annualReturnRate - annualCostRate);
				double netGain = netInvestVal - initialLumpsum - annualContribution * numberOfYears;
				double feePaid = investObj.getFeePaid() + investObj.getNetInvestmentValue() * annualCostRate;
				double diminishCompoundingLost = grossGain - netGain - feePaid;
				double totalLostToFee = feePaid + diminishCompoundingLost;
				double trexScore = netGain / grossGain;
				
				investObj.setGrossInvestmentValue(grossInvestVal);				
				investObj.setGrossGain(grossGain);
				investObj.setNetInvestmentValue(netInvestVal);
				investObj.setNetGain(netGain);
				investObj.setFeePaid(feePaid);
				investObj.setDiminishCompoundingLost(diminishCompoundingLost);
				investObj.setTotalLostToFee(totalLostToFee);
				investObj.setTrexScore(trexScore);
				
				if (investmentValueDetails == null) {
					investmentValueDetails = new ArrayList<InvestmentValueObject>();
				}
				investmentValueDetails.add(investObj.clone(investObj)); // store the investment values for each year
				
				return calculate(numberOfYears - 1, investObj);
			}
		}
	}
	
	class InvestmentValueObject implements Cloneable {
		private double grossInvestmentValue = 0.0d; // in $ amount
		private double grossGain = 0.0d; // in $ amount
		private double netInvestmentValue = 0.0d; // in $ amount
		private double netGain = 0.0d; // in $ amount
		private double feePaid = 0.0d; // in $ amount
		private double diminishCompoundingLost = 0.0d; // in $ amount
		private double totalLostToFee = 0.0d; // in $ amount
		private double trexScore = 0.0d; // Total Return Efficiency Index(T-REX) Score developed by Larry Bates (https://larrybates.ca/t-rex-score/), in percentage
		public double getGrossInvestmentValue() {
			return grossInvestmentValue;
		}
		public void setGrossInvestmentValue(double grossInvestmentValue) {
			this.grossInvestmentValue = grossInvestmentValue;
		}
		public double getGrossGain() {
			return grossGain;
		}
		public void setGrossGain(double grossGain) {
			this.grossGain = grossGain;
		}
		public double getNetInvestmentValue() {
			return netInvestmentValue;
		}
		public void setNetInvestmentValue(double netInvestmentValue) {
			this.netInvestmentValue = netInvestmentValue;
		}
		public double getNetGain() {
			return netGain;
		}
		public void setNetGain(double netGain) {
			this.netGain = netGain;
		}
		public double getFeePaid() {
			return feePaid;
		}
		public void setFeePaid(double feePaid) {
			this.feePaid = feePaid;
		}
		public double getDiminishCompoundingLost() {
			return diminishCompoundingLost;
		}
		public void setDiminishCompoundingLost(double diminishCompoundingLost) {
			this.diminishCompoundingLost = diminishCompoundingLost;
		}
		public double getTotalLostToFee() {
			return totalLostToFee;
		}
		public void setTotalLostToFee(double totalLostToFee) {
			this.totalLostToFee = totalLostToFee;
		}
		public double getTrexScore() {
			return trexScore;
		}
		public void setTrexScore(double trexScore) {
			this.trexScore = trexScore;
		}
		
		public InvestmentValueObject clone(InvestmentValueObject obj) {
		    InvestmentValueObject copiedObj = new InvestmentValueObject();
		    copiedObj.setGrossInvestmentValue(obj.getGrossInvestmentValue());
		    copiedObj.setGrossGain(obj.getGrossGain());
		    copiedObj.setNetInvestmentValue(obj.getNetInvestmentValue());
		    copiedObj.setNetGain(obj.getNetGain());
		    copiedObj.setFeePaid(obj.getFeePaid());
		    copiedObj.setDiminishCompoundingLost(obj.getDiminishCompoundingLost());
		    copiedObj.setTotalLostToFee(obj.getTotalLostToFee());
		    copiedObj.setTrexScore(obj.getTrexScore());
		    
		    return copiedObj;
		}
		
		@Override
		public String toString() {
			return "InvestmentValueObject [grossInvestmentValue=" + InvestmentCalculator.formatDecimalValue(grossInvestmentValue) + 
					", grossGain=" + InvestmentCalculator.formatDecimalValue(grossGain) +
					", netInvestmentValue=" + InvestmentCalculator.formatDecimalValue(netInvestmentValue) +
					", netGain=" + InvestmentCalculator.formatDecimalValue(netGain) +
					", feePaid=" + InvestmentCalculator.formatDecimalValue(feePaid) + 
					", diminishCompoundingLost=" + InvestmentCalculator.formatDecimalValue(diminishCompoundingLost) +
					", totalLostToFee=" + InvestmentCalculator.formatDecimalValue(totalLostToFee) + 
					", trexScore=" + InvestmentCalculator.formatPercentageValue(trexScore) + "]";
		}
		
	}
}
