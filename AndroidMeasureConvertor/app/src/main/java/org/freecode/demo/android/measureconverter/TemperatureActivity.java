package org.freecode.demo.android.measureconverter;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TemperatureActivity extends SharedActivity {
    private EditText etxCelsius = null;
    private EditText etxFahrenheit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        etxCelsius = (EditText)findViewById(R.id.etxCelsius);
        etxFahrenheit = (EditText)findViewById(R.id.etxFahrenheit);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.removeItem(R.id.miTemperature);
        return super.onPrepareOptionsMenu(menu);
    }

    public void clearFields(View v) {
        if (etxCelsius != null) {
            etxCelsius.setText("");
        }
        if (etxFahrenheit != null) {
            etxFahrenheit.setText("");
        }
    }

    public void calculate(View v) {
        Double dCelsiusVal = null;
        Double dFahrenheitVal = null;

        if ( (etxCelsius.getText() == null || etxCelsius.getText().toString().length() < 1) &&
                (etxFahrenheit.getText() == null || etxFahrenheit.getText().toString().length() < 1) )
        {
            Toast.makeText(this, "Please enter either a Celsius or Fahrenheit value.", Toast.LENGTH_SHORT).show();
        }
        else if ( etxCelsius.getText() != null && etxCelsius.getText().toString().length() > 0 )
        {
            dCelsiusVal = Double.parseDouble(etxCelsius.getText().toString());
            dFahrenheitVal = MeasureCalculator.celsius2Fahrenheit( dCelsiusVal );
            etxFahrenheit.setText( MeasureCalculator.formatValue( dFahrenheitVal) );
        }
        else if ( etxFahrenheit.getText() != null && etxFahrenheit.getText().toString().length() > 0 )
        {
            dFahrenheitVal = Double.parseDouble( etxFahrenheit.getText().toString());
            dCelsiusVal= MeasureCalculator.fahrenheit2Celsius( dFahrenheitVal );
            etxCelsius.setText( MeasureCalculator.formatValue( dCelsiusVal ) );
        }
    }
}
