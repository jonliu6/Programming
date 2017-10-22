package org.freecode.demo.android.measureconverter;

import android.icu.util.Measure;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class VolumeActivity extends SharedActivity {
    private EditText etxTbsp = null;
    private EditText etxFloz = null;
    private EditText etxCup = null;
    private EditText etxPint = null;
    private EditText etxQuart = null;
    private EditText etxLitre = null;
    private EditText etxGallon = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

        etxTbsp = (EditText) findViewById(R.id.etxTbsp);
        etxFloz = (EditText) findViewById(R.id.etxFloz);
        etxCup = (EditText) findViewById(R.id.etxCup);
        etxPint = (EditText) findViewById(R.id.etxPint);
        etxQuart = (EditText) findViewById(R.id.etxQuart);
        etxLitre = (EditText) findViewById(R.id.etxLitre);
        etxGallon = (EditText) findViewById(R.id.etxGallon);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.removeItem(R.id.miVolume);
        return super.onPrepareOptionsMenu(menu);
    }

    public void clearFields(View v) {
        if (etxTbsp != null) {
            etxTbsp.setText("");
        }
        if (etxFloz != null) {
            etxFloz.setText("");
        }
        if (etxCup != null) {
            etxCup.setText("");
        }
        if (etxPint != null) {
            etxPint.setText("");
        }
        if (etxQuart != null) {
            etxQuart.setText("");
        }
        if (etxLitre != null) {
            etxLitre.setText("");
        }
        if (etxGallon != null) {
            etxGallon.setText("");
        }
    }

    public void calculate(View v) {
        Double dTbsp = null;
        Double dFloz = null;
        Double dCup = null;
        Double dPint = null;
        Double dQuart = null;
        Double dLitre = null;
        Double dGallon = null;

        if ( (etxTbsp.getText() == null || etxTbsp.getText().toString().length() < 1) &&
                (etxFloz.getText() == null || etxFloz.getText().toString().length() < 1) &&
                (etxCup.getText() == null || etxCup.getText().toString().length() < 1) &&
                (etxPint.getText() == null || etxPint.getText().toString().length() < 1) &&
                (etxQuart.getText() == null || etxQuart.getText().toString().length() < 1) &&
                (etxLitre.getText() == null || etxLitre.getText().toString().length() < 1) &&
                (etxGallon.getText() == null || etxGallon.getText().toString().length() < 1) )
        {
            Toast.makeText(this, "Please enter either any Volume value.", Toast.LENGTH_SHORT).show();
        }
        else if ( etxTbsp.getText() != null && etxTbsp.getText().toString().length() > 0 )
        {
            dTbsp = Double.parseDouble(etxTbsp.getText().toString());
            dFloz = MeasureCalculator.tbsp2Floz(dTbsp);
            etxFloz.setText(MeasureCalculator.formatValue(dFloz));
            dCup = MeasureCalculator.floz2Cup(dFloz);
            etxCup.setText(MeasureCalculator.formatValue(dCup));
            dGallon = MeasureCalculator.cup2Gallon(dCup);
            etxGallon.setText(MeasureCalculator.formatValue(dGallon));
            dLitre = MeasureCalculator.gallon2Litre(dGallon);
            etxLitre.setText(MeasureCalculator.formatValue(dLitre));
            dPint = MeasureCalculator.gallon2Pint(dGallon);
            etxPint.setText(MeasureCalculator.formatValue(dPint));
            dQuart = MeasureCalculator.gallon2Quart(dGallon);
            etxQuart.setText(MeasureCalculator.formatValue(dQuart));
        }
        else if ( etxFloz.getText() != null && etxFloz.getText().toString().length() > 0 )
        {
            dFloz = Double.parseDouble(etxFloz.getText().toString());
            dCup = MeasureCalculator.floz2Cup(dFloz);
            etxCup.setText(MeasureCalculator.formatValue(dCup));
            dGallon = MeasureCalculator.cup2Gallon(dCup);
            etxGallon.setText(MeasureCalculator.formatValue(dGallon));
            dLitre = MeasureCalculator.gallon2Litre(dGallon);
            etxLitre.setText(MeasureCalculator.formatValue(dLitre));
            dPint = MeasureCalculator.gallon2Pint(dGallon);
            etxPint.setText(MeasureCalculator.formatValue(dPint));
            dQuart = MeasureCalculator.gallon2Quart(dGallon);
            etxQuart.setText(MeasureCalculator.formatValue(dQuart));
            dTbsp = MeasureCalculator.floz2Tbsp(dFloz);
            etxTbsp.setText(MeasureCalculator.formatValue(dTbsp));
        }
        else if ( etxCup.getText() != null && etxCup.getText().toString().length() > 0 )
        {
            dCup = Double.parseDouble(etxCup.getText().toString());
            dGallon = MeasureCalculator.cup2Gallon(dCup);
            etxGallon.setText(MeasureCalculator.formatValue(dGallon));
            dLitre = MeasureCalculator.gallon2Litre(dGallon);
            etxLitre.setText(MeasureCalculator.formatValue(dLitre));
            dPint = MeasureCalculator.gallon2Pint(dGallon);
            etxPint.setText(MeasureCalculator.formatValue(dPint));
            dQuart = MeasureCalculator.gallon2Quart(dGallon);
            etxQuart.setText(MeasureCalculator.formatValue(dQuart));
            dFloz = MeasureCalculator.cup2Floz(dCup);
            etxFloz.setText(MeasureCalculator.formatValue(dFloz));
            dTbsp = MeasureCalculator.floz2Tbsp(dFloz);
            etxTbsp.setText(MeasureCalculator.formatValue(dTbsp));
        }
        else if ( etxPint.getText() != null && etxPint.getText().toString().length() > 0 ) {
            dPint = Double.parseDouble(etxPint.getText().toString());
            dGallon = MeasureCalculator.pint2Gallon(dPint);
            etxGallon.setText(MeasureCalculator.formatValue(dGallon));
            dLitre = MeasureCalculator.gallon2Litre(dGallon);
            etxLitre.setText(MeasureCalculator.formatValue(dLitre));
            dQuart = MeasureCalculator.gallon2Quart(dGallon);
            etxQuart.setText(MeasureCalculator.formatValue(dQuart));
            dCup = MeasureCalculator.gallon2Cup(dGallon);
            etxCup.setText(MeasureCalculator.formatValue(dCup));
            dFloz = MeasureCalculator.cup2Floz(dCup);
            etxFloz.setText(MeasureCalculator.formatValue(dFloz));
            dTbsp = MeasureCalculator.floz2Tbsp(dFloz);
            etxTbsp.setText(MeasureCalculator.formatValue(dTbsp));
        }
        else if ( etxQuart.getText() != null && etxQuart.getText().toString().length() > 0 ) {
            dQuart = Double.parseDouble(etxQuart.getText().toString());
            dGallon = MeasureCalculator.quart2Gallon(dQuart);
            etxGallon.setText(MeasureCalculator.formatValue(dGallon));
            dLitre = MeasureCalculator.gallon2Litre(dGallon);
            etxLitre.setText(MeasureCalculator.formatValue(dLitre));
            dPint = MeasureCalculator.gallon2Pint(dGallon);
            etxPint.setText(MeasureCalculator.formatValue(dPint));
            dCup = MeasureCalculator.gallon2Cup(dGallon);
            etxCup.setText(MeasureCalculator.formatValue(dCup));
            dFloz = MeasureCalculator.cup2Floz(dCup);
            etxFloz.setText(MeasureCalculator.formatValue(dFloz));
            dTbsp = MeasureCalculator.floz2Tbsp(dFloz);
            etxTbsp.setText(MeasureCalculator.formatValue(dTbsp));
        }
        else if ( etxLitre.getText() != null && etxLitre.getText().toString().length() > 0 ) {
            dLitre = Double.parseDouble(etxLitre.getText().toString());
            dGallon = MeasureCalculator.litre2Gallon(dLitre);
            etxGallon.setText(MeasureCalculator.formatValue(dGallon));
            dPint = MeasureCalculator.gallon2Pint(dGallon);
            etxPint.setText(MeasureCalculator.formatValue(dPint));
            dQuart = MeasureCalculator.gallon2Quart(dGallon);
            etxQuart.setText(MeasureCalculator.formatValue(dQuart));
            dCup = MeasureCalculator.gallon2Cup(dGallon);
            etxCup.setText(MeasureCalculator.formatValue(dCup));
            dFloz = MeasureCalculator.cup2Floz(dCup);
            etxFloz.setText(MeasureCalculator.formatValue(dFloz));
            dTbsp = MeasureCalculator.floz2Tbsp(dFloz);
            etxTbsp.setText(MeasureCalculator.formatValue(dTbsp));
        }
        else if ( etxGallon.getText() != null && etxGallon.getText().toString().length() > 0 ) {
            dGallon = Double.parseDouble(etxGallon.getText().toString());
            dLitre = MeasureCalculator.gallon2Litre(dGallon);
            etxLitre.setText(MeasureCalculator.formatValue(dLitre));
            dPint = MeasureCalculator.gallon2Pint(dGallon);
            etxPint.setText(MeasureCalculator.formatValue(dPint));
            dQuart = MeasureCalculator.gallon2Quart(dGallon);
            etxQuart.setText(MeasureCalculator.formatValue(dQuart));
            dCup = MeasureCalculator.gallon2Cup(dGallon);
            etxCup.setText(MeasureCalculator.formatValue(dCup));
            dFloz = MeasureCalculator.cup2Floz(dCup);
            etxFloz.setText(MeasureCalculator.formatValue(dFloz));
            dTbsp = MeasureCalculator.floz2Tbsp(dFloz);
            etxTbsp.setText(MeasureCalculator.formatValue(dTbsp));
        }
    }
}
