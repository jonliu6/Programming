package org.freecode.demo.measureconvertor;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class WeightActivity extends Activity {

	EditText etxPound = null;
	EditText etxOunce = null;
	EditText etxGram = null;
	EditText etxKilogram = null;
	EditText etxQuarter = null;
	
	WeightCalculator calculator = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weight);
		
		etxPound = (EditText) findViewById(R.id.etxPound);
		etxOunce = (EditText) findViewById(R.id.etxOunce);
		etxGram = (EditText) findViewById(R.id.etxGram);
		etxKilogram = (EditText) findViewById(R.id.etxKilogram);
		etxQuarter = (EditText) findViewById(R.id.etxQuarter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weight, menu);
		return true;
	}
	
	public void closeActivity(View v)
	{
		finish();
	}
	
	public void clearFields(View v)
	{
		if ( etxPound != null )
		{
			etxPound.setText("");
		}
		if ( etxOunce != null )
		{
			etxOunce.setText("");
		}
		if ( etxGram != null )
		{
			etxGram.setText("");
		}
		if ( etxKilogram != null )
		{
			etxKilogram.setText("");
		}
		if ( etxQuarter != null )
		{
			etxQuarter.setText("");
		}
	}

	public void calculateMeasures( View v)
	{
		Double dPound = null;
		Double dOunce = null;
		Double dGram = null;
		Double dKilogram = null;
		Double dQuarter = null;
		
		calculator = new WeightCalculator();
		
		if ( (etxPound.getText() == null || etxPound.getText().toString().length() < 1) &&
				(etxOunce.getText() == null || etxOunce.getText().toString().length() < 1) &&
				(etxGram.getText() == null || etxGram.getText().toString().length() < 1) && 
				(etxKilogram.getText() == null || etxKilogram.getText().toString().length() < 1) &&
				(etxQuarter.getText() == null || etxQuarter.getText().toString().length() < 1) )
		{
			Toast.makeText(this, "Please enter any of the Weight fields.", Toast.LENGTH_SHORT).show();
		}
		
		else if ( etxPound.getText() != null && etxPound.getText().toString().length() > 0 )
		{
			dPound = Double.parseDouble(etxPound.getText().toString());
			
			dOunce = calculator.calcOunceByPound( dPound );
			etxOunce.setText( calculator.formatValue( dOunce) );
			dGram = calculator.calcGramByPound( dPound );
			etxGram.setText( calculator.formatValue( dGram) );
			dKilogram = calculator.calcKilogramByPound( dPound );
			etxKilogram.setText( calculator.formatValue( dKilogram) );
			dQuarter = calculator.calcQuarterByPound( dPound );
			etxQuarter.setText( calculator.formatValue( dQuarter ));
		}
		
		else if ( etxOunce.getText() != null && etxOunce.getText().toString().length() > 0 )
		{
			dOunce = Double.parseDouble(etxOunce.getText().toString());
			
			dPound = calculator.calcPoundByOunce(dOunce);
			etxPound.setText( calculator.formatValue( dPound) );
			dGram = calculator.calcGramByOunce( dOunce );
			etxGram.setText( calculator.formatValue( dGram) );
			dKilogram = calculator.calcKilogramByOunce( dOunce );
			etxKilogram.setText( calculator.formatValue( dKilogram) );
			dQuarter = calculator.calcQuarterByOunce( dOunce );
			etxQuarter.setText( calculator.formatValue( dQuarter ));
		}
		
		else if ( etxGram.getText() != null && etxGram.getText().toString().length() > 0 )
		{
			dGram = Double.parseDouble(etxGram.getText().toString());
			
			dOunce = calculator.calcOunceByGram( dGram );
			etxOunce.setText( calculator.formatValue( dOunce) );
			dPound = calculator.calcPoundByGram( dGram );
			etxPound.setText( calculator.formatValue( dPound) );
			dKilogram = calculator.calcKilogramByGram( dGram );
			etxKilogram.setText( calculator.formatValue( dKilogram) );
			dQuarter = calculator.calcQuarterByGram( dGram );
			etxQuarter.setText( calculator.formatValue( dQuarter ));
		}
		
		else if ( etxKilogram.getText() != null && etxKilogram.getText().toString().length() > 0 )
		{
			dKilogram = Double.parseDouble(etxKilogram.getText().toString());
			
			dOunce = calculator.calcOunceByKilogram( dKilogram );
			etxOunce.setText( calculator.formatValue( dOunce) );
			dPound = calculator.calcPoundByKilogram( dKilogram );
			etxPound.setText( calculator.formatValue( dPound) );
			dGram = calculator.calcGramByKilogram( dKilogram );
			etxGram.setText( calculator.formatValue( dGram) );
			dQuarter = calculator.calcQuarterByKiloGram( dKilogram );
			etxQuarter.setText( calculator.formatValue( dQuarter ));
		}
		
		else if ( etxQuarter.getText() != null && etxQuarter.getText().toString().length() > 0 )
		{
			dQuarter = Double.parseDouble(etxQuarter.getText().toString());
			
			dOunce = calculator.calcOunceByQuarter( dQuarter );
			etxOunce.setText( calculator.formatValue( dOunce) );
			dPound = calculator.calcPoundByQuarter( dQuarter );
			etxPound.setText( calculator.formatValue( dPound) );
			dGram = calculator.calcGramByQuarter( dQuarter );
			etxGram.setText( calculator.formatValue( dGram) );
			dKilogram = calculator.calcKilogramByQuarter( dQuarter );
			etxKilogram.setText( calculator.formatValue( dKilogram ));
		}
		
	}

}
