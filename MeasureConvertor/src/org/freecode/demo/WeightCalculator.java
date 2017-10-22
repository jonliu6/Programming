package org.freecode.demo;

public class WeightCalculator extends AbstractMeasureCalculator {
	private final Double POUND_TO_KILOGRAM = 0.453592;
	private final Double POUND_TO_OUNCE = 16d;
	private final Double KILOGRAM_TO_GRAM = 1000d;
	private final Double QUARTER_TO_POUND  = 27.777778;
	private final Double OUNCE_TO_GRAM = 28.3495;
	
	public Double calcPoundByKilogram( Double kgValue )
	{
		return kgValue / POUND_TO_KILOGRAM;
	}
	
	public Double calcPoundByOunce(Double ozValue )
	{
		return ozValue / POUND_TO_OUNCE;
	}
	
	public Double calcPoundByQuarter(Double qrtValue )
	{
		return qrtValue * QUARTER_TO_POUND;
	}
	
	public Double calcPoundByGram(Double gValue)
	{
		return gValue / KILOGRAM_TO_GRAM / POUND_TO_KILOGRAM;
	}
	
	public Double calcKilogramByPound( Double lbValue )
	{
		return lbValue * POUND_TO_KILOGRAM;
	}
	
	public Double calcKilogramByGram( Double gValue )
	{
		return gValue / KILOGRAM_TO_GRAM;
	}
	
	public Double calcKilogramByOunce( Double ozValue )
	{
		return ozValue * OUNCE_TO_GRAM / KILOGRAM_TO_GRAM;
	}
	
	public Double calcKilogramByQuarter( Double qrtValue )
	{
		return qrtValue * QUARTER_TO_POUND * POUND_TO_KILOGRAM;
	}
	
	public Double calcGramByKilogram( Double kgValue )
	{
		return kgValue * KILOGRAM_TO_GRAM;
	}
	
	public Double calcGramByPound( Double lbValue )
	{
		return lbValue * POUND_TO_KILOGRAM * KILOGRAM_TO_GRAM;
	}
	
	public Double calcGramByOunce( Double ozValue )
	{
		return ozValue * OUNCE_TO_GRAM;
	}
	
	public Double calcGramByQuarter( Double qrtValue )
	{
		return qrtValue * QUARTER_TO_POUND * POUND_TO_KILOGRAM * KILOGRAM_TO_GRAM;
	}
	
	public Double calcOunceByPound( Double lbValue )
	{
		return lbValue * POUND_TO_OUNCE;
	}
	
	public Double calcOunceByGram( Double gValue )
	{
		return gValue / OUNCE_TO_GRAM / POUND_TO_OUNCE;
	}
	
	public Double calcOunceByKilogram( Double kgValue )
	{
		return kgValue / POUND_TO_KILOGRAM * POUND_TO_OUNCE;
	}
	
	public Double calcOunceByQuarter( Double qrtValue )
	{
		return qrtValue * QUARTER_TO_POUND * POUND_TO_OUNCE;
	}
	
	public Double calcQuarterByPound( Double lbValue )
	{
		return lbValue / QUARTER_TO_POUND ;
	}
	
	public Double calcQuarterByGram( Double gValue )
	{
		return gValue / KILOGRAM_TO_GRAM / POUND_TO_KILOGRAM / QUARTER_TO_POUND;
	}
	
	public Double calcQuarterByKiloGram( Double kgValue )
	{
		return kgValue / POUND_TO_KILOGRAM / QUARTER_TO_POUND;
	}
	
	public Double calcQuarterByOunce( Double ozValue )
	{
		return ozValue / POUND_TO_OUNCE / QUARTER_TO_POUND;
	}

}
