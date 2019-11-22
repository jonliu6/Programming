package org.freecode.demo.android.measureconverter;

import java.text.DecimalFormat;

/**
 * Created by tyler on 2017-08-31.
 */

public class MeasureCalculator {
    public final static String ONE_DECIMAL_PLACE = "###.###";

    public static String formatValue( Double aValue )
    {
        DecimalFormat df = new DecimalFormat(ONE_DECIMAL_PLACE);
        String formattedValue = null;

        if ( aValue != null )
        {
            formattedValue = df.format(aValue);
        }

        return formattedValue;
    }

    // Temperature
    public static Double celsius2Fahrenheit(Double cVal) {
        return cVal * 9 / 5 + 32;
    }

    public static Double fahrenheit2Celsius(Double fVal) {
        return (fVal - 32) * 5 / 9;
    }

    // Length
    public final static Double YARD_TO_METER = 0.9144d;
    public final static Double MILE_TO_METER = 1609.34d;
    public final static Double METER_TO_FOOT = 3.2808d;
    public final static Double INCH_TO_CENTIMETER = 2.54d;
    public final static Double FOOT_TO_INCH = 12d;

    public static Double centimeter2Inch(Double cVal) {
        return cVal / INCH_TO_CENTIMETER;
    }
    public static Double inch2Centimeter(Double iVal) {
        return iVal * INCH_TO_CENTIMETER;
    }

    public static Double inch2Foot(Double iVal) {
        return iVal / FOOT_TO_INCH;
    }
    public static Double foot2Inch(Double fVal) {
        return fVal * FOOT_TO_INCH;
    }

    public static Double foot2Meter(Double fVal) {
        return fVal / METER_TO_FOOT;
    }
    public static Double meter2Foot(Double mVal) {
        return mVal * METER_TO_FOOT;
    }

    public static Double meter2Yard(Double mVal) {
        return mVal / YARD_TO_METER;
    }
    public static Double yard2Meter(Double yVal) {
        return yVal * YARD_TO_METER;
    }

    public static Double meter2Mile(Double mVal) {
        return mVal / MILE_TO_METER;
    }
    public static Double mile2Meter(Double mlVal) {
        return mlVal * MILE_TO_METER;
    }

    public static String formatFootInchValue(Double val) {
        Integer ftVal = (int)(Math.floor(val));
        Double inVal = val - ftVal;
        if (inVal > 0) {
            return ftVal + "'" + Math.round(inVal * FOOT_TO_INCH) + "\"";
        }
        else {
            return String.valueOf(val);
        }
    }

    // Weight
    public final static Double OUNCE_TO_GRAM = 28.3495d;
    public final static Double POUND_TO_OUNCE = 16d;
    public final static Double POUND_TO_KILOGRAM = 0.453592d;
    public final static Double QUARTER_TO_POUND  = 27.777778d;

    public static Double gram2Ounce(Double gVal) {
        return gVal / OUNCE_TO_GRAM;
    }
    public static Double ounce2Gram(Double oVal) {
        return oVal * OUNCE_TO_GRAM;
    }

    public static Double ounce2Pound(Double oVal) {
        return oVal / POUND_TO_OUNCE;
    }
    public static Double pound2Ounce(Double pVal) {
        return pVal * POUND_TO_OUNCE;
    }

    public static Double pound2Kilogram(Double pVal) {
        return pVal * POUND_TO_KILOGRAM;
    }
    public static Double kilogram2Pound(Double kgVal) {
        return kgVal / POUND_TO_KILOGRAM;
    }

    public static Double pound2Quarter(Double pVal) {
        return pVal / QUARTER_TO_POUND;
    }
    public static Double quarter2Pound(Double qVal) {
        return qVal * QUARTER_TO_POUND;
    }

    // Volume
    public final static Double FLOZ_TO_TBSP = 2d;
    public final static Double CUP_TO_FLOZ = 8d;
    public final static Double GALLON_TO_CUP = 16d;
    public final static Double GALLON_TO_PINT = 8d;
    public final static Double GALLON_TO_QUART = 4d;
    public final static Double GALLON_TO_LITRE = 3.78541d;

    public static Double tbsp2Floz(Double tbspVal) {
        return tbspVal / FLOZ_TO_TBSP;
    }
    public static Double floz2Tbsp(Double flozVal) {
        return flozVal * FLOZ_TO_TBSP;
    }

    public static Double floz2Cup(Double flozVal) {
        return flozVal / CUP_TO_FLOZ;
    }
    public static Double cup2Floz(Double cupVal) {
        return cupVal * CUP_TO_FLOZ;
    }

    public static Double cup2Gallon(Double cupVal) {
        return cupVal / GALLON_TO_CUP;
    }
    public static Double gallon2Cup(Double galVal) {
        return galVal * GALLON_TO_CUP;
    }

    public static Double pint2Gallon(Double ptVal) {
        return ptVal / GALLON_TO_PINT;
    }
    public static Double gallon2Pint(Double galVal) {
        return galVal * GALLON_TO_PINT;
    }

    public static Double quart2Gallon(Double qtVal) {
        return qtVal / GALLON_TO_QUART;
    }
    public static Double gallon2Quart(Double galVal) {
        return galVal * GALLON_TO_QUART;
    }

    public static Double litre2Gallon(Double lVal) {
        return lVal / GALLON_TO_LITRE;
    }
    public static Double gallon2Litre(Double galVal) {
        return galVal * GALLON_TO_LITRE;
    }

    // Area
    public final static Double SQFT_TO_SQMT = 0.09290304d;
    public final static Double ACRE_TO_SQMT = 4046.86;

    public static Double sqFeet2SqMeter(Double sqftVal) {
        return sqftVal * SQFT_TO_SQMT;
    }
    public static Double sqMeter2SqFeet(Double sqmtVal) {
        return sqmtVal / SQFT_TO_SQMT;
    }
    public static Double acre2SqMeter(Double acreVal) { return acreVal * ACRE_TO_SQMT; }
    public static Double sqMeter2Acre(Double sqmtVal) { return sqmtVal / ACRE_TO_SQMT; }
}
