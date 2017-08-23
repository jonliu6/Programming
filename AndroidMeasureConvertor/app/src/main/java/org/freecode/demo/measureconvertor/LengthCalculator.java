package org.freecode.demo.measureconvertor;

public class LengthCalculator extends AbstractMeasureCalculator {
	
	private final Double YARD_TO_METER = 0.9144;
	private final Double YARD_TO_FOOT = 3.0d;
	private final Double MILE_TO_METER = 1609.34d;
	
	public Double calcYardByMile(Double mileValue)
	{
		return calcYardByMeter(calcMeterByMile(mileValue));
	}
	public Double calcCentimeterByMile(Double mileValue)
	{
		return calcCentimeterByMeter(calcMeterByMile(mileValue));
	}
	public Double calcInchByMile( Double mileValue )
	{
		return calcInchByMeter(calcMeterByMile(mileValue));
	}
	public Double calcFootByMile(Double mileValue)
	{
		return calcFootByMeter(calcMeterByMile(mileValue));
	}
	public Double calcMileByYard(Double yardValue)
	{
		return calcMileByMeter(calcMeterByYard(yardValue));
	}
	public Double calcMileByCentimeter(Double cetiMeterValue )
	{
		return calcMileByMeter(calcMeterByCentimeter(cetiMeterValue));
	}
	public Double calcMileByFoot(Double footValue)
	{
		return calcMileByMeter(calcMeterByFoot(footValue));
	}
	public Double calcMileByInch(Double inchValue)
	{
		return calcMileByMeter(calcMeterByInch(inchValue));
	}
	public Double calcMileByMeter(Double meterValue)
	{
		return meterValue / MILE_TO_METER;
	}
	
	public Double calcMeterByMile(Double mileValue)
	{
		return mileValue * MILE_TO_METER;
	}

	public Double calcInchByFoot (Double footValue )
	{
		return footValue * 12;
	}
	
	public Double calcInchByCentimeter( Double centimeterValue )
    {
    	return centimeterValue / 2.54;
    }
	
	public Double calcInchByMeter( Double meterValue )
    {
    	return calcInchByCentimeter(calcCentimeterByMeter(meterValue));
    }
	
	public Double calcFootByInch( Double inchValue )
    {
    	return inchValue / 12;
    }
	
	public Double calcFootByCentimeter( Double centimeterValue )
    {
    	return calcFootByInch(calcInchByCentimeter(centimeterValue));
    }
	
	public Double calcFootByMeter( Double meterValue )
    {
    	return meterValue * 3.2808;
    }
	
	public Double calcCentimeterByInch( Double inchValue )
    {
    	return inchValue * 2.54;
    }
	
	public Double calcCentimeterByFoot( Double footValue )
    {
    	return calcCentimeterByInch(calcInchByFoot(footValue));
    }
	
	public Double calcCentimeterByMeter( Double meterValue )
    {
    	return meterValue * 100;
    }
    
    public Double calcMeterByInch( Double inchValue )
    {
    	return calcMeterByCentimeter(calcCentimeterByInch(inchValue));
    }
    
    public Double calcMeterByFoot( Double footValue )
    {
    	return footValue / 3.2808;
    }
    
    public Double calcMeterByCentimeter( Double centimeterValue )
    {
    	return centimeterValue / 100;
    }
    
    public Double calcYardByMeter(Double meterValue )
    {
    	return meterValue / YARD_TO_METER; 
    }
    
    public Double calcMeterByYard(Double yardValue )
    {
    	return yardValue * YARD_TO_METER;
    }
    
    public Double calcYardByFoot(Double footValue )
    {
    	return footValue / YARD_TO_FOOT; 
    }
    
    public Double calcFootByYard(Double yardValue)
    {
    	return yardValue * YARD_TO_FOOT;
    }
    
    public Double calcYardByInch(Double inchValue )
    {
    	return calcYardByFoot( calcFootByInch(inchValue) ); 
    }
    
    public Double calcInchByYard(Double yardValue)
    {
    	return calcInchByFoot( calcFootByYard(yardValue) );
    }
    
    public Double calcYardByCentimeter(Double centimeterValue )
    {
    	return calcYardByMeter(calcMeterByCentimeter(centimeterValue));
    }
    
    public Double calcCentimeterByYard(Double yardValue)
    {
    	return calcCentimeterByMeter( calcMeterByYard(yardValue));
    }
    
    
}
