package org.freecode.demo;

import java.text.DecimalFormat;

public class AbstractMeasureCalculator implements MeasureCalculator {
	public final static String ONE_DECIMAL_PLACE = "###.###";
	
	public String formatValue( Double aValue )
	{
		DecimalFormat df = new DecimalFormat(ONE_DECIMAL_PLACE);
		String formattedValue = null;
		
		if ( aValue != null )
		{
			formattedValue = df.format(aValue);
		}
		
		return formattedValue;
	}
}
