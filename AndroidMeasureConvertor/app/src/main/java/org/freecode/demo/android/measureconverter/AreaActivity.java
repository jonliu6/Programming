package org.freecode.demo.android.measureconverter;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AreaActivity extends SharedActivity {
    private EditText etxSqft = null;
    private EditText etxSqm = null;
    private EditText etxAcre = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        etxSqft = (EditText) findViewById(R.id.etxSqFt);
        etxSqm = (EditText) findViewById(R.id.etxSqm);
        etxAcre = (EditText) findViewById(R.id.etxAcre);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.removeItem(R.id.miArea);
        return super.onPrepareOptionsMenu(menu);
    }

    public void clearFields(View v) {
        if (etxSqft != null) {
            etxSqft.setText("");
        }
        if (etxSqm != null) {
            etxSqm.setText("");
        }
        if (etxAcre != null) {
            etxAcre.setText("");
        }
    }

    public void calculate(View v) {
        Double dSqFt = null;
        Double dSqM = null;
        Double dAcre = null;

        if ( (etxSqft.getText() == null || etxSqft.getText().toString().length() < 1) &&
                (etxSqm.getText() == null || etxSqm.getText().toString().length() < 1) &&
                (etxAcre.getText() == null || etxAcre.getText().toString().length() < 1) )
        {
            Toast.makeText(this, "Please enter either any Area value.", Toast.LENGTH_SHORT).show();
        }
        else if ( etxSqft.getText() != null && etxSqft.getText().toString().length() > 0 )
        {
            dSqFt = Double.parseDouble(etxSqft.getText().toString());
            dSqM = MeasureCalculator.sqFeet2SqMeter( dSqFt );
            etxSqm.setText( MeasureCalculator.formatValue( dSqM) );
            dAcre = MeasureCalculator.sqMeter2Acre(dSqM);
            etxAcre.setText(MeasureCalculator.formatValue(dAcre));
        }
        else if ( etxSqm.getText() != null && etxSqm.getText().toString().length() > 0 )
        {
            dSqM = Double.parseDouble( etxSqm.getText().toString());
            dSqFt= MeasureCalculator.sqMeter2SqFeet( dSqM );
            etxSqft.setText( MeasureCalculator.formatValue( dSqFt ) );
            dAcre = MeasureCalculator.sqMeter2Acre(dSqM);
            etxAcre.setText(MeasureCalculator.formatValue(dAcre));
        }
        else if ( etxAcre.getText() != null && etxAcre.getText().toString().length() > 0 )
        {
            dAcre = Double.parseDouble( etxAcre.getText().toString());
            dSqM = MeasureCalculator.acre2SqMeter(dAcre);
            etxSqm.setText(MeasureCalculator.formatValue(dSqM));
            dSqFt = MeasureCalculator.sqMeter2SqFeet(dSqM);
            etxSqft.setText(MeasureCalculator.formatValue(dSqFt));
        }
    }
}
