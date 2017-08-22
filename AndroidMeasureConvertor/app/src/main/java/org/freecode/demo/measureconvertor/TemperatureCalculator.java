package org.freecode.demo.measureconvertor;

public class TemperatureCalculator extends AbstractMeasureCalculator {
	
	public Double calcCelsius( Double fahrenheitValue )
    {
    	return (fahrenheitValue - 32) * 5/9;
    }
    
    public Double calcFahrenheit( Double celsiusValue )
    {
    	return celsiusValue * 9/5 + 32;
    }
}
