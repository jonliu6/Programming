package org.freecode.demo;

import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TemperatureActivity extends Activity {
	EditText etxCelsius = null;
	EditText etxFahrenheit = null;
	TemperatureCalculator calculator = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temperature);
		
		etxCelsius = (EditText) findViewById(R.id.etxCelsius);
		etxFahrenheit = (EditText) findViewById(R.id.etxFahrenheit);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.temperature, menu);
		return true;
	}
	
	/**
	 * close the activity
	 * @param v
	 */
	public void closeActivity(View v)
	{
		finish();
	}
	
	/**
	 * clean up all the fields
	 * @param v
	 */
	public void clearFields(View v)
	{
		if ( etxCelsius != null )
		{
			etxCelsius.setText("");
		}
		if ( etxFahrenheit != null )
		{
			etxFahrenheit.setText("");
		}
	}

	/**
	 * based on the user input, calculate Celsius or Fahrenheit values
	 * @param v
	 */
	public void calculateMeasures( View v)
	{
		Double dCelsiusVal = null;
		Double dFahrenheitVal = null;
		
		calculator = new TemperatureCalculator();
		
		// Toast.makeText(this, (etxCelsius.getText() == null) + "," + etxCelsius.getText().toString().length(), Toast.LENGTH_SHORT).show();
		if ( (etxCelsius.getText() == null || etxCelsius.getText().toString().length() < 1) && 
				(etxFahrenheit.getText() == null || etxFahrenheit.getText().toString().length() < 1) )
		{
			Toast.makeText(this, "Please enter either a Celsius or Fahrenheit value.", Toast.LENGTH_SHORT).show();
		}
		else if ( etxCelsius.getText() != null && etxCelsius.getText().toString().length() > 0 )
		{
			dCelsiusVal = Double.parseDouble(etxCelsius.getText().toString());
			dFahrenheitVal = calculator.calcFahrenheit( dCelsiusVal );
			etxFahrenheit.setText( calculator.formatValue( dFahrenheitVal) );
		}
		else if ( etxFahrenheit.getText() != null && etxFahrenheit.getText().toString().length() > 0 )
		{
			dFahrenheitVal = Double.parseDouble( etxFahrenheit.getText().toString());
			dCelsiusVal= calculator.calcCelsius( dFahrenheitVal );
			etxCelsius.setText( calculator.formatValue( dCelsiusVal ) );
		}
	}
}
