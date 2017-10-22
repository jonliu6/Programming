package org.freecode.demo.android.measureconverter;

import android.icu.util.Measure;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class WeightActivity extends SharedActivity {
    private EditText etxGram = null;
    private EditText etxOunce = null;
    private EditText etxPound = null;
    private EditText etxKilogram = null;
    private EditText etxQuarter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        etxGram = (EditText) findViewById(R.id.etxGram);
        etxOunce = (EditText) findViewById(R.id.etxOunce);
        etxPound = (EditText) findViewById(R.id.etxPound);
        etxKilogram = (EditText) findViewById(R.id.etxKilogram);
        etxQuarter = (EditText) findViewById(R.id.etxQuarter);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.removeItem(R.id.miWeight);
        return super.onPrepareOptionsMenu(menu);
    }

    public void clearFields(View v) {
        if (etxGram != null) {
            etxGram.setText("");
        }
        if (etxOunce != null) {
            etxOunce.setText("");
        }
        if (etxPound != null) {
            etxPound.setText("");
        }
        if (etxKilogram != null) {
            etxKilogram.setText("");
        }
        if (etxQuarter != null) {
            etxQuarter.setText("");
        }
    }

    public void calculate(View v) {
        Double dGram = null;
        Double dOunce = null;
        Double dPound = null;
        Double dKilogram = null;
        Double dQuarter = null;

        if ( (etxGram.getText() == null || etxGram.getText().toString().length() < 1) &&
                (etxOunce.getText() == null || etxOunce.getText().toString().length() < 1) &&
                (etxPound.getText() == null || etxPound.getText().toString().length() < 1) &&
                (etxKilogram.getText() == null || etxKilogram.getText().toString().length() < 1) &&
                (etxQuarter.getText() == null || etxQuarter.getText().toString().length() < 1) )
        {
            Toast.makeText(this, "Please enter either any Weight value.", Toast.LENGTH_SHORT).show();
        }
        else if ( etxGram.getText() != null && etxGram.getText().toString().length() > 0 )
        {
            dGram = Double.parseDouble(etxGram.getText().toString());
            dOunce = MeasureCalculator.gram2Ounce(dGram);
            etxOunce.setText( MeasureCalculator.formatValue( dOunce) );
            dPound = MeasureCalculator.ounce2Pound(dOunce);
            etxPound.setText( MeasureCalculator.formatValue(dPound) );
            dKilogram = MeasureCalculator.pound2Kilogram(dPound);
            etxKilogram.setText( MeasureCalculator.formatValue(dKilogram) );
            dQuarter = MeasureCalculator.pound2Quarter(dPound);
            etxQuarter.setText(MeasureCalculator.formatValue(dQuarter));
        }
        else if ( etxOunce.getText() != null && etxOunce.getText().toString().length() > 0 )
        {
            dOunce = Double.parseDouble(etxOunce.getText().toString());
            dPound = MeasureCalculator.ounce2Pound(dOunce);
            etxPound.setText( MeasureCalculator.formatValue(dPound) );
            dKilogram = MeasureCalculator.pound2Kilogram(dPound);
            etxKilogram.setText( MeasureCalculator.formatValue(dKilogram) );
            dQuarter = MeasureCalculator.pound2Quarter(dPound);
            etxQuarter.setText(MeasureCalculator.formatValue(dQuarter));
            dGram = MeasureCalculator.ounce2Gram(dOunce);
            etxGram.setText(MeasureCalculator.formatValue(dGram));
        }
        else if ( etxPound.getText() != null && etxPound.getText().toString().length() > 0 ) {
            dPound = Double.parseDouble(etxPound.getText().toString());
            dKilogram = MeasureCalculator.pound2Kilogram(dPound);
            etxKilogram.setText( MeasureCalculator.formatValue(dKilogram) );
            dQuarter = MeasureCalculator.pound2Quarter(dPound);
            etxQuarter.setText(MeasureCalculator.formatValue(dQuarter));
            dOunce = MeasureCalculator.pound2Ounce(dPound);
            etxOunce.setText(MeasureCalculator.formatValue(dOunce));
            dGram = MeasureCalculator.ounce2Gram(dOunce);
            etxGram.setText(MeasureCalculator.formatValue(dGram));
        }
        else if ( etxKilogram.getText() != null && etxKilogram.getText().toString().length() > 0 ) {
            dKilogram = Double.parseDouble(etxKilogram.getText().toString());
            dPound = MeasureCalculator.kilogram2Pound(dKilogram);
            etxPound.setText(MeasureCalculator.formatValue(dPound));
            dQuarter = MeasureCalculator.pound2Quarter(dPound);
            etxQuarter.setText(MeasureCalculator.formatValue(dQuarter));
            dOunce = MeasureCalculator.pound2Ounce(dPound);
            etxOunce.setText(MeasureCalculator.formatValue(dOunce));
            dGram = MeasureCalculator.ounce2Gram(dOunce);
            etxGram.setText(MeasureCalculator.formatValue(dGram));
        }
        else if ( etxQuarter.getText() != null && etxQuarter.getText().toString().length() > 0 ) {
            dQuarter = Double.parseDouble(etxQuarter.getText().toString());
            dPound = MeasureCalculator.quarter2Pound(dQuarter);
            etxPound.setText(MeasureCalculator.formatValue(dPound));
            dKilogram = MeasureCalculator.pound2Kilogram(dPound);
            etxKilogram.setText( MeasureCalculator.formatValue(dKilogram) );
            dOunce = MeasureCalculator.pound2Ounce(dPound);
            etxOunce.setText(MeasureCalculator.formatValue(dOunce));
            dGram = MeasureCalculator.ounce2Gram(dOunce);
            etxGram.setText(MeasureCalculator.formatValue(dGram));
        }
    }
}
