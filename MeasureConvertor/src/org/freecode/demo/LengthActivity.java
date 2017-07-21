package org.freecode.demo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LengthActivity extends Activity {
	
	EditText etxInch = null;
	EditText etxFoot = null;
	EditText etxCentimeter = null;
	EditText etxMeter = null;
	EditText etxYard = null;
	EditText etxMile = null;
	
	LengthCalculator calculator = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_length);
		
		etxInch = (EditText) findViewById(R.id.etxInch);
		etxFoot = (EditText) findViewById(R.id.etxFoot);
		etxCentimeter = (EditText) findViewById(R.id.etxCentimeter);
		etxMeter = (EditText) findViewById(R.id.etxMeter);
		etxYard = (EditText) findViewById(R.id.etxYard);
		etxMile = (EditText) findViewById(R.id.etxMile);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.length, menu);
		return true;
	}
	
	public void closeActivity(View v)
	{
		finish();
	}
	
	public void clearFields(View v)
	{
		if ( etxInch != null )
		{
			etxInch.setText("");
		}
		if ( etxFoot != null )
		{
			etxFoot.setText("");
		}
		if ( etxCentimeter != null )
		{
			etxCentimeter.setText("");
		}
		if ( etxMeter != null )
		{
			etxMeter.setText("");
		}
		if ( etxYard != null )
		{
			etxYard.setText("");
		}
		if ( etxMile != null )
		{
			etxMile.setText("");
		}
	}

	public void calculateMeasures( View v)
	{
		Double dInch = null;
		Double dFoot = null;
		Double dCentimeter = null;
		Double dMeter = null;
		Double dYard = null;
		Double dMile = null;
		
		calculator = new LengthCalculator();
		
		if ( (etxInch.getText() == null || etxInch.getText().toString().length() < 1) &&
				(etxFoot.getText() == null || etxFoot.getText().toString().length() < 1) &&
				(etxCentimeter.getText() == null || etxCentimeter.getText().toString().length() < 1) && 
				(etxMeter.getText() == null || etxMeter.getText().toString().length() < 1) &&
				(etxYard.getText() == null || etxYard.getText().toString().length() < 1) &&
				(etxMile.getText() == null || etxMile.getText().toString().length() < 1))
		{
			Toast.makeText(this, "Please enter any of the Length fields.", Toast.LENGTH_SHORT).show();
		}
		
		else if ( etxInch.getText() != null && etxInch.getText().toString().length() > 0 )
		{
			dInch = Double.parseDouble(etxInch.getText().toString());
			
			dFoot = calculator.calcFootByInch( dInch );
			etxFoot.setText( calculator.formatValue( dFoot) );
			dCentimeter = calculator.calcCentimeterByInch( dInch );
			etxCentimeter.setText( calculator.formatValue( dCentimeter) );
			dMeter = calculator.calcMeterByInch( dInch );
			etxMeter.setText( calculator.formatValue( dMeter) );
			dYard = calculator.calcYardByInch( dInch );
			etxYard.setText( calculator.formatValue( dYard) );
			dMile = calculator.calcMileByInch( dInch );
			etxMile.setText( calculator.formatValue( dMile ));
		}
		
		else if ( etxFoot.getText() != null && etxFoot.getText().toString().length() > 0 )
		{
			dFoot = Double.parseDouble(etxFoot.getText().toString());
			
			dInch = calculator.calcInchByFoot(dFoot);
			etxInch.setText( calculator.formatValue( dInch) );
			dCentimeter = calculator.calcCentimeterByFoot( dFoot );
			etxCentimeter.setText( calculator.formatValue( dCentimeter) );
			dMeter = calculator.calcMeterByFoot( dFoot );
			etxMeter.setText( calculator.formatValue( dMeter) );
			dYard = calculator.calcYardByFoot( dFoot );
			etxYard.setText( calculator.formatValue( dYard) );
			dMile = calculator.calcMileByFoot( dFoot );
			etxMile.setText( calculator.formatValue( dMile ));
		}
		
		else if ( etxCentimeter.getText() != null && etxCentimeter.getText().toString().length() > 0 )
		{
			dCentimeter = Double.parseDouble(etxCentimeter.getText().toString());
			
			dFoot = calculator.calcFootByCentimeter( dCentimeter );
			etxFoot.setText( calculator.formatValue( dFoot) );
			dInch = calculator.calcInchByCentimeter( dCentimeter );
			etxInch.setText( calculator.formatValue( dInch) );
			dMeter = calculator.calcMeterByCentimeter( dCentimeter );
			etxMeter.setText( calculator.formatValue( dMeter) );
			dYard = calculator.calcYardByCentimeter( dCentimeter );
			etxYard.setText( calculator.formatValue( dYard) );
			dMile = calculator.calcMileByCentimeter( dCentimeter );
			etxMile.setText( calculator.formatValue( dMile ));
		}
		
		else if ( etxMeter.getText() != null && etxMeter.getText().toString().length() > 0 )
		{
			dMeter = Double.parseDouble(etxMeter.getText().toString());
			
			dFoot = calculator.calcFootByMeter( dMeter );
			etxFoot.setText( calculator.formatValue( dFoot) );
			dInch = calculator.calcInchByMeter( dMeter );
			etxInch.setText( calculator.formatValue( dInch) );
			dCentimeter = calculator.calcCentimeterByMeter( dMeter );
			etxCentimeter.setText( calculator.formatValue( dCentimeter) );
			dYard = calculator.calcYardByMeter( dMeter );
			etxYard.setText( calculator.formatValue( dYard) );
			dMile = calculator.calcMileByMeter( dMeter );
			etxMile.setText( calculator.formatValue( dMile ));
		}
		
		else if ( etxYard.getText() != null && etxYard.getText().toString().length() > 0 )
		{
			dYard = Double.parseDouble(etxYard.getText().toString());
			
			dFoot = calculator.calcFootByYard( dYard );
			etxFoot.setText( calculator.formatValue( dFoot) );
			dInch = calculator.calcInchByYard( dYard );
			etxInch.setText( calculator.formatValue( dInch) );
			dCentimeter = calculator.calcCentimeterByYard( dYard );
			etxCentimeter.setText( calculator.formatValue( dCentimeter) );
			dMeter = calculator.calcMeterByYard( dYard );
			etxMeter.setText( calculator.formatValue( dMeter) );
			dMile = calculator.calcMileByYard( dYard );
			etxMile.setText( calculator.formatValue( dMile ));
		}
		else if ( etxMile.getText() != null && etxMile.getText().toString().length() > 0 )
		{
			dMile = Double.parseDouble(etxMile.getText().toString());
			
			dFoot = calculator.calcFootByMile( dMile );
			etxFoot.setText( calculator.formatValue( dFoot) );
			dInch = calculator.calcInchByMile( dMile );
			etxInch.setText( calculator.formatValue( dInch) );
			dCentimeter = calculator.calcCentimeterByMile( dMile );
			etxCentimeter.setText( calculator.formatValue( dCentimeter) );
			dMeter = calculator.calcMeterByMile( dMile );
			etxMeter.setText( calculator.formatValue( dMeter) );
			dYard = calculator.calcYardByMile( dMile );
			etxYard.setText( calculator.formatValue( dYard ));
		}
	}

}
