package org.freecode.demo.android.measureconverter;

import android.icu.util.Measure;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LengthActivity extends SharedActivity {
    private EditText etxCentimeter = null;
    private EditText etxInch = null;
    private EditText etxFoot = null;
    private EditText etxYard = null;
    private EditText etxMeter = null;
    private EditText etxMile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        etxCentimeter = (EditText) findViewById(R.id.etxCentimeter);
        etxInch = (EditText) findViewById(R.id.etxInch);
        etxFoot = (EditText) findViewById(R.id.etxFoot);
        etxYard = (EditText) findViewById(R.id.etxYard);
        etxMeter = (EditText) findViewById(R.id.etxMeter);
        etxMile = (EditText) findViewById(R.id.etxMile);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.removeItem(R.id.miLength);
        return super.onPrepareOptionsMenu(menu);
    }

    public void showFormattedFtInVal(View v) {
        Double dFoot = null;
        String formattedVal = null;
        if ( etxFoot.getText() != null && etxFoot.getText().toString().length() > 0 ) {
            dFoot = Double.parseDouble( etxFoot.getText().toString());
            formattedVal = MeasureCalculator.formatFootInchValue(dFoot);
            if (formattedVal != null && formattedVal.length() > 0) {
                Toast.makeText(this, formattedVal, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void clearFields(View v) {
        if (etxCentimeter != null) {
            etxCentimeter.setText("");
        }
        if (etxInch != null) {
            etxInch.setText("");
        }
        if (etxFoot != null) {
            etxFoot.setText("");
        }
        if (etxYard != null) {
            etxYard.setText("");
        }
        if (etxMeter != null) {
            etxMeter.setText("");
        }
        if (etxMile != null) {
            etxMile.setText("");
        }
    }

    public void calculate(View v) {
        Double dCentimeter = null;
        Double dInch = null;
        Double dFoot = null;
        Double dYard = null;
        Double dMeter = null;
        Double dMile = null;

        if ( (etxCentimeter.getText() == null || etxCentimeter.getText().toString().length() < 1) &&
                (etxInch.getText() == null || etxInch.getText().toString().length() < 1) &&
                (etxFoot.getText() == null || etxFoot.getText().toString().length() < 1) &&
                (etxYard.getText() == null || etxYard.getText().toString().length() < 1) &&
                (etxMeter.getText() == null || etxMeter.getText().toString().length() < 1) &&
                (etxMile.getText() == null || etxMile.getText().toString().length() < 1) )
        {
            Toast.makeText(this, "Please enter either any Length value.", Toast.LENGTH_SHORT).show();
        }
        else if ( etxCentimeter.getText() != null && etxCentimeter.getText().toString().length() > 0 )
        {
            dCentimeter = Double.parseDouble(etxCentimeter.getText().toString());
            dInch = MeasureCalculator.centimeter2Inch(dCentimeter);
            etxInch.setText( MeasureCalculator.formatValue( dInch) );
            dFoot = MeasureCalculator.inch2Foot(dInch);
            etxFoot.setText( MeasureCalculator.formatValue(dFoot) );
            dMeter = MeasureCalculator.foot2Meter(dFoot);
            etxMeter.setText( MeasureCalculator.formatValue(dMeter) );
            dYard = MeasureCalculator.meter2Yard(dMeter);
            etxYard.setText(MeasureCalculator.formatValue(dYard));
            dMile = MeasureCalculator.meter2Mile(dMeter);
            etxMile.setText( MeasureCalculator.formatValue(dMile) );
        }
        else if ( etxInch.getText() != null && etxInch.getText().toString().length() > 0 )
        {
            dInch = Double.parseDouble( etxInch.getText().toString());
            dFoot = MeasureCalculator.inch2Foot(dInch);
            etxFoot.setText( MeasureCalculator.formatValue(dFoot) );
            dMeter = MeasureCalculator.foot2Meter(dFoot);
            etxMeter.setText( MeasureCalculator.formatValue(dMeter) );
            dYard = MeasureCalculator.meter2Yard(dMeter);
            etxYard.setText(MeasureCalculator.formatValue(dYard));
            dMile = MeasureCalculator.meter2Mile(dMeter);
            etxMile.setText( MeasureCalculator.formatValue(dMile) );
            dCentimeter = MeasureCalculator.inch2Centimeter(dInch);
            etxCentimeter.setText(MeasureCalculator.formatValue(dCentimeter));
        }
        else if ( etxFoot.getText() != null && etxFoot.getText().toString().length() > 0 ) {
            dFoot = Double.parseDouble( etxFoot.getText().toString());
            dMeter = MeasureCalculator.foot2Meter(dFoot);
            etxMeter.setText( MeasureCalculator.formatValue(dMeter) );
            dYard = MeasureCalculator.meter2Yard(dMeter);
            etxYard.setText(MeasureCalculator.formatValue(dYard));
            dMile = MeasureCalculator.meter2Mile(dMeter);
            etxMile.setText( MeasureCalculator.formatValue(dMile) );
            dInch = MeasureCalculator.foot2Inch(dFoot);
            etxInch.setText( MeasureCalculator.formatValue( dInch) );
            dCentimeter = MeasureCalculator.inch2Centimeter(dInch);
            etxCentimeter.setText(MeasureCalculator.formatValue(dCentimeter));
        }
        else if ( etxYard.getText() != null && etxYard.getText().toString().length() > 0 ) {
            dYard = Double.parseDouble( etxYard.getText().toString());
            dMeter = MeasureCalculator.yard2Meter(dYard);
            etxMeter.setText( MeasureCalculator.formatValue(dMeter) );
            dMile = MeasureCalculator.meter2Mile(dMeter);
            etxMile.setText( MeasureCalculator.formatValue(dMile) );
            dFoot = MeasureCalculator.meter2Foot(dMeter);
            etxFoot.setText( MeasureCalculator.formatValue(dFoot) );
            dInch = MeasureCalculator.foot2Inch(dFoot);
            etxInch.setText( MeasureCalculator.formatValue( dInch) );
            dCentimeter = MeasureCalculator.inch2Centimeter(dInch);
            etxCentimeter.setText(MeasureCalculator.formatValue(dCentimeter));
        }
        else if ( etxMeter.getText() != null && etxMeter.getText().toString().length() > 0 ) {
            dMeter = Double.parseDouble( etxMeter.getText().toString());
            dYard = MeasureCalculator.meter2Yard(dMeter);
            etxYard.setText(MeasureCalculator.formatValue(dYard));
            dMile = MeasureCalculator.meter2Mile(dMeter);
            etxMile.setText( MeasureCalculator.formatValue(dMile) );
            dFoot = MeasureCalculator.meter2Foot(dMeter);
            etxFoot.setText( MeasureCalculator.formatValue(dFoot) );
            dInch = MeasureCalculator.foot2Inch(dFoot);
            etxInch.setText( MeasureCalculator.formatValue( dInch) );
            dCentimeter = MeasureCalculator.inch2Centimeter(dInch);
            etxCentimeter.setText(MeasureCalculator.formatValue(dCentimeter));
        }
        else if ( etxMile.getText() != null && etxMile.getText().toString().length() > 0 ) {
            dMile = Double.parseDouble( etxMile.getText().toString());
            dMeter = MeasureCalculator.mile2Meter(dMile);
            etxMeter.setText( MeasureCalculator.formatValue(dMeter) );
            dYard = MeasureCalculator.meter2Yard(dMeter);
            etxYard.setText(MeasureCalculator.formatValue(dYard));
            dFoot = MeasureCalculator.meter2Foot(dMeter);
            etxFoot.setText( MeasureCalculator.formatValue(dFoot) );
            dInch = MeasureCalculator.foot2Inch(dFoot);
            etxInch.setText( MeasureCalculator.formatValue( dInch) );
            dCentimeter = MeasureCalculator.inch2Centimeter(dInch);
            etxCentimeter.setText(MeasureCalculator.formatValue(dCentimeter));
        }
    }
}
