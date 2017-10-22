package org.freecode.demo.android.measureconverter;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AreaActivity extends SharedActivity {
    private EditText etxSqft = null;
    private EditText etxSqm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        etxSqft = (EditText) findViewById(R.id.etxSqFt);
        etxSqm = (EditText) findViewById(R.id.etxSqm);
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
    }

    public void calculate(View v) {
        Double dSqFt = null;
        Double dSqM = null;

        if ( (etxSqft.getText() == null || etxSqft.getText().toString().length() < 1) &&
                (etxSqm.getText() == null || etxSqm.getText().toString().length() < 1) )
        {
            Toast.makeText(this, "Please enter either any Area value.", Toast.LENGTH_SHORT).show();
        }
        else if ( etxSqft.getText() != null && etxSqft.getText().toString().length() > 0 )
        {
            dSqFt = Double.parseDouble(etxSqft.getText().toString());
            dSqM = MeasureCalculator.sqFeet2SqMeter( dSqFt );
            etxSqm.setText( MeasureCalculator.formatValue( dSqM) );
        }
        else if ( etxSqm.getText() != null && etxSqm.getText().toString().length() > 0 )
        {
            dSqM = Double.parseDouble( etxSqm.getText().toString());
            dSqFt= MeasureCalculator.sqMeter2SqFeet( dSqM );
            etxSqft.setText( MeasureCalculator.formatValue( dSqFt ) );
        }
    }
}
