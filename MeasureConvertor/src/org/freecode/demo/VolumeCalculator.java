package org.freecode.demo;

public class VolumeCalculator extends AbstractMeasureCalculator {
    private final Double GALLON_TO_LITRE = 3.78541;
    private final Double LITRE_TO_FLOZ = 33.814;
    private final Double GALLON_TO_FLOZ = 128.0d;
    private final Double GALLON_TO_PINT = 8.0d;
    private final Double GALLON_TO_QUART = 4.0d;
    private final Double GALLON_TO_USCUP = 16.0d;
    private final Double QUART_TO_USCUP = 4.0d;
    
    public Double calcGallonByUSCup( Double usCupValue )
    {
    	return usCupValue / GALLON_TO_USCUP;
    }
    public Double calcFlozByUSCup( Double usCupValue )
    {
    	return calcFlozByGallon( calcGallonByUSCup( usCupValue ) );
    }
    public Double calcLitreByUSCup( Double usCupValue )
    {
        return calcLitreByGallon( calcGallonByUSCup( usCupValue ) );
    }
    public Double calcPintByUSCup( Double usCupValue )
    {
    	return calcPintByGallon( calcGallonByUSCup(usCupValue));
    }
    public Double calcQuartByUSCup( Double usCupValue )
    {
    	return usCupValue / QUART_TO_USCUP;
    }
    
    public Double calcUSCupByPint( Double pintValue )
    {
    	return GALLON_TO_USCUP * calcGallonbyPint(pintValue);
    }
    public Double calcUSCupByFloz( Double flozValue )
    {
    	return GALLON_TO_USCUP * calcGallonByFloz(flozValue);
    }
    public Double calcUSCupByLitre ( Double litreValue ) 
    {
    	return GALLON_TO_USCUP * calcGallonByLitre(litreValue);
    }
    public Double calcUSCupByGallon( Double gallonValue )
    {
    	return gallonValue * GALLON_TO_USCUP;
    }
    
    public Double calcUSCupByQuart( Double quartValue )
    {
    	return quartValue * QUART_TO_USCUP;
    }
    
    public Double calcGallonByLitre( Double litreValue )
    {
    	return litreValue / GALLON_TO_LITRE;
    }
    
    public Double calcGallonByFloz( Double flozValue )
    {
    	return flozValue / GALLON_TO_FLOZ;
    }
    
    public Double calcGallonbyPint( Double pintValue )
    {
    	return pintValue / GALLON_TO_PINT;
    }
    
    public Double calcGallonByQuart( Double quartValue)
    {
    	return quartValue / GALLON_TO_QUART;
    }
    
    public Double calcLitreByGallon(Double gallonvalue)
    {
    	return gallonvalue * GALLON_TO_LITRE;
    }
    
    public Double calcFlozByGallon( Double gallonValue )
    {
    	return gallonValue * GALLON_TO_FLOZ;
    }

    public Double calcPintByGallon( Double gallonValue )
    {
    	return gallonValue * GALLON_TO_PINT;
    }
    
    public Double calcQuartByGallon( Double gallonValue )
    {
    	return gallonValue * GALLON_TO_QUART;
    }
    
    // Derivable formulas
    public Double calcLitreByFloz(Double flozValue)
    {
    	return calcLitreByGallon( calcGallonByFloz(flozValue) );
    }
    
    public Double calcLitreByPint(Double pintValue)
    {
    	return calcLitreByGallon( calcGallonbyPint( pintValue ));
    }
    
    public Double calcLitreByQuart( Double quartValue )
    {
    	return calcLitreByGallon( calcGallonByQuart(quartValue));
    }
    
    public Double calcFlozByLitre( Double litreValue )
    {
    	return calcFlozByGallon( calcGallonByLitre(litreValue));
    }
    
    public Double calcFlozByPint( Double pintValue )
    {
    	return calcFlozByGallon( calcGallonbyPint(pintValue));
    }
    
    public Double calcFlozByQuart( Double quartValue )
    {
    	return calcFlozByGallon( calcGallonByQuart(quartValue));
    }
    
    public Double calcPintByLitre( Double litreValue )
    {
    	return calcPintByGallon(calcGallonByLitre(litreValue));
    }
    
    public Double calcPintByFloz( Double flozValue )
    {
    	return calcPintByGallon( calcGallonByFloz(flozValue));
    }
    
    public Double calcPintByQuart( Double quartValue )
    {
    	return calcPintByGallon( calcGallonByQuart(quartValue));
    }
    
    public Double calcQuartByLitre( Double litreValue )
    {
    	return calcQuartByGallon(calcGallonByLitre(litreValue));
    }
    
    public Double calcQuartByFloz( Double flozValue )
    {
    	return calcQuartByGallon(calcGallonByFloz(flozValue));
    }
    
    public Double calcQuartByPint( Double pintValue )
    {
    	return calcQuartByGallon(calcGallonbyPint(pintValue));
    }
}
