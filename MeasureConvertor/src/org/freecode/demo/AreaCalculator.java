package org.freecode.demo;

public class AreaCalculator extends AbstractMeasureCalculator{
	private final Double SQRFT_TO_SQRM = 0.09290304;

	public Double calcSqrFeetBySqrMeter(Double sqrMetervalue )
	{
		return sqrMetervalue / SQRFT_TO_SQRM;
	}
	
	public Double calcSqrMeterBySqrFeet(Double sqrFeetValue)
	{
		return sqrFeetValue * SQRFT_TO_SQRM;
	}
}
