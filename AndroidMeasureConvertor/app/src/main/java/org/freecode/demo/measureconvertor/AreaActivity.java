package org.freecode.demo.measureconvertor;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AreaActivity extends Activity {

	EditText etxSqrFt = null;
	EditText etxSqrM = null;
	AreaCalculator calculator = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_area);
		
		etxSqrFt = (EditText) findViewById(R.id.etxSqrFt);
		etxSqrM = (EditText) findViewById(R.id.etxSqrM);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.area, menu);
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
		if ( etxSqrFt != null )
		{
			etxSqrFt.setText("");
		}
		if ( etxSqrM != null )
		{
			etxSqrM.setText("");
		}
	}

	/**
	 * based on the user input, calculate Sqr. Feet or Sqr. Meters values
	 * @param v
	 */
	public void calculateMeasures( View v)
	{
		Double dSqrFt = null;
		Double dSqrM = null;
		
		calculator = new AreaCalculator();
		
		// Toast.makeText(this, (etxSqrFt.getText() == null) + "," + etxSqrFt.getText().toString().length(), Toast.LENGTH_SHORT).show();
		if ( (etxSqrFt.getText() == null || etxSqrFt.getText().toString().length() < 1) && 
				(etxSqrM.getText() == null || etxSqrM.getText().toString().length() < 1) )
		{
			Toast.makeText(this, "Please enter either a Celsius or Fahrenheit value.", Toast.LENGTH_SHORT).show();
		}
		else if ( etxSqrFt.getText() != null && etxSqrFt.getText().toString().length() > 0 )
		{
			dSqrFt = Double.parseDouble(etxSqrFt.getText().toString());
			dSqrM = calculator.calcSqrMeterBySqrFeet( dSqrFt );
			etxSqrM.setText( calculator.formatValue( dSqrM) );
		}
		else if ( etxSqrM.getText() != null && etxSqrM.getText().toString().length() > 0 )
		{
			dSqrM = Double.parseDouble( etxSqrM.getText().toString());
			dSqrFt= calculator.calcSqrFeetBySqrMeter( dSqrM );
			etxSqrFt.setText( calculator.formatValue( dSqrFt ) );
		}
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.action_temperature:
				Toast.makeText(this, "Temperature...", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.action_length:
				Toast.makeText(this, "Length...", Toast.LENGTH_SHORT).show();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
