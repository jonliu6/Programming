package org.freecode.demo.measureconvertor;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class VolumeActivity extends Activity {

	EditText etxLitre = null;
	EditText etxFloz = null;
	EditText etxPint = null;
	EditText etxQuart = null;
	EditText etxGallon = null;
	EditText etxUSCup = null;
	
	VolumeCalculator calculator = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volume);
		
		etxLitre = (EditText) findViewById(R.id.etxLitre);
		etxFloz = (EditText) findViewById(R.id.etxFloz);
		etxPint = (EditText) findViewById(R.id.etxPint);
		etxQuart = (EditText) findViewById(R.id.etxQuart);
		etxGallon = (EditText) findViewById(R.id.etxGallon);
		etxUSCup = (EditText) findViewById(R.id.etxUSCup);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.volume, menu);
		return true;
	}

	public void closeActivity(View v)
	{
		finish();
	}
	
	public void clearFields(View v)
	{
		if ( etxLitre != null )
		{
			etxLitre.setText("");
		}
		if ( etxFloz != null )
		{
			etxFloz.setText("");
		}
		if ( etxPint != null )
		{
			etxPint.setText("");
		}
		if ( etxQuart != null )
		{
			etxQuart.setText("");
		}
		if ( etxGallon != null )
		{
			etxGallon.setText("");
		}
		if ( etxUSCup != null )
		{
			etxUSCup.setText("");
		}
	}

	public void calculateMeasures( View v)
	{
		Double dLitre = null;
		Double dFloz = null;
		Double dPint = null;
		Double dQuart = null;
		Double dGallon = null;
		Double dUSCup = null;
		
		calculator = new VolumeCalculator();
		
		if ( (etxLitre.getText() == null || etxLitre.getText().toString().length() < 1) &&
				(etxFloz.getText() == null || etxFloz.getText().toString().length() < 1) &&
				(etxPint.getText() == null || etxPint.getText().toString().length() < 1) && 
				(etxQuart.getText() == null || etxQuart.getText().toString().length() < 1) &&
				(etxGallon.getText() == null || etxGallon.getText().toString().length() < 1) && 
				(etxUSCup.getText() == null || etxUSCup.getText().toString().length() < 1))
		{
			Toast.makeText(this, "Please enter any of the Weight fields.", Toast.LENGTH_SHORT).show();
		}
		
		else if ( etxLitre.getText() != null && etxLitre.getText().toString().length() > 0 )
		{
			dLitre = Double.parseDouble(etxLitre.getText().toString());
			
			dFloz = calculator.calcFlozByLitre( dLitre );
			etxFloz.setText( calculator.formatValue( dFloz) );
			dPint = calculator.calcPintByLitre( dLitre );
			etxPint.setText( calculator.formatValue( dPint) );
			dQuart = calculator.calcQuartByLitre( dLitre );
			etxQuart.setText( calculator.formatValue( dQuart) );
			dGallon = calculator.calcGallonByLitre( dLitre );
			etxGallon.setText( calculator.formatValue( dGallon ));
			dUSCup = calculator.calcUSCupByLitre( dLitre );
			etxUSCup.setText( calculator.formatValue( dUSCup ));
		}
		
		else if ( etxFloz.getText() != null && etxFloz.getText().toString().length() > 0 )
		{
			dFloz = Double.parseDouble(etxFloz.getText().toString());
			
			dLitre = calculator.calcLitreByFloz(dFloz);
			etxLitre.setText( calculator.formatValue( dLitre) );
			dPint = calculator.calcPintByFloz( dFloz );
			etxPint.setText( calculator.formatValue( dPint) );
			dQuart = calculator.calcQuartByFloz( dFloz );
			etxQuart.setText( calculator.formatValue( dQuart) );
			dGallon = calculator.calcGallonByFloz( dFloz );
			etxGallon.setText( calculator.formatValue( dGallon ));
			dUSCup = calculator.calcUSCupByFloz( dFloz );
			etxUSCup.setText( calculator.formatValue( dUSCup ));
		}
		
		else if ( etxPint.getText() != null && etxPint.getText().toString().length() > 0 )
		{
			dPint = Double.parseDouble(etxPint.getText().toString());
			
			dFloz = calculator.calcFlozByPint( dPint );
			etxFloz.setText( calculator.formatValue( dFloz) );
			dLitre = calculator.calcLitreByPint( dPint );
			etxLitre.setText( calculator.formatValue( dLitre) );
			dQuart = calculator.calcQuartByPint( dPint );
			etxQuart.setText( calculator.formatValue( dQuart) );
			dGallon = calculator.calcGallonbyPint( dPint );
			etxGallon.setText( calculator.formatValue( dGallon ));
		    dUSCup = calculator.calcUSCupByPint( dPint );
		    etxUSCup.setText( calculator.formatValue( dUSCup ));
		}
		
		else if ( etxQuart.getText() != null && etxQuart.getText().toString().length() > 0 )
		{
			dQuart = Double.parseDouble(etxQuart.getText().toString());
			
			dFloz = calculator.calcFlozByQuart( dQuart );
			etxFloz.setText( calculator.formatValue( dFloz) );
			dLitre = calculator.calcLitreByQuart( dQuart );
			etxLitre.setText( calculator.formatValue( dLitre) );
			dPint = calculator.calcPintByQuart( dQuart );
			etxPint.setText( calculator.formatValue( dPint) );
			dGallon = calculator.calcGallonByQuart( dQuart );
			etxGallon.setText( calculator.formatValue( dGallon ));
			dUSCup = calculator.calcUSCupByQuart(dQuart);
			etxUSCup.setText( calculator.formatValue( dUSCup ));
		}
		
		else if ( etxGallon.getText() != null && etxGallon.getText().toString().length() > 0 )
		{
			dGallon = Double.parseDouble(etxGallon.getText().toString());
			
			dFloz = calculator.calcFlozByGallon( dGallon );
			etxFloz.setText( calculator.formatValue( dFloz) );
			dLitre = calculator.calcLitreByGallon( dGallon );
			etxLitre.setText( calculator.formatValue( dLitre) );
			dPint = calculator.calcPintByGallon( dGallon );
			etxPint.setText( calculator.formatValue( dPint) );
			dQuart = calculator.calcQuartByGallon( dGallon );
			etxQuart.setText( calculator.formatValue( dQuart ));
			dUSCup = calculator.calcUSCupByGallon( dGallon );
			etxUSCup.setText( calculator.formatValue( dUSCup ));
		}
		
		else if ( etxUSCup.getText() != null && etxUSCup.getText().toString().length() > 0 )
		{
			dUSCup = Double.parseDouble(etxUSCup.getText().toString());
			
			dFloz = calculator.calcFlozByUSCup( dGallon );
			etxFloz.setText( calculator.formatValue( dFloz) );
			dLitre = calculator.calcLitreByUSCup( dGallon );
			etxLitre.setText( calculator.formatValue( dLitre) );
			dPint = calculator.calcPintByUSCup( dGallon );
			etxPint.setText( calculator.formatValue( dPint) );
			dQuart = calculator.calcQuartByUSCup( dGallon );
			etxQuart.setText( calculator.formatValue( dQuart ));
			dGallon = calculator.calcGallonByUSCup( dUSCup );
			etxGallon.setText( calculator.formatValue(dGallon));
		}
		
	}
}
