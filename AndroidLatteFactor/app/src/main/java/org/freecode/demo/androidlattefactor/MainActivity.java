package org.freecode.demo.androidlattefactor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etxInitialValue = null;
    EditText etxRegularContribution = null;
    EditText etxGrowthRate = null;
    EditText etxCount = null;
    TextView txResult = null;
    LatteFactor latteFactor = null;
    boolean hasAlertPermission = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // check and request user permission
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            Intent actMgr = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//            startActivity(actMgr);
//        }

        etxInitialValue = findViewById(R.id.etxInitialValue);
        etxRegularContribution = findViewById(R.id.etxRegularContribution);
        etxGrowthRate = findViewById(R.id.etxGrowthRate);
        etxCount = findViewById(R.id.etxCount);
        txResult = findViewById(R.id.txResult);
    }

    public void calculate(View v) {
        Double initVal = null;
        Double regularContribution = null;
        Double growthRate = null;
        Integer count = null;
        CharSequence msg = getText(R.string.missing_inputs);

        try {
            if (etxInitialValue != null && etxInitialValue.getText() != null && etxInitialValue.getText().length() > 0) {
                initVal = Double.parseDouble(etxInitialValue.getText().toString());
            }

            if (etxRegularContribution != null && etxRegularContribution.getText() != null && etxRegularContribution.getText().length() > 0) {
                regularContribution = Double.parseDouble(etxRegularContribution.getText().toString());
            }

            if (etxGrowthRate != null && etxGrowthRate.getText() != null && etxGrowthRate.getText().length() > 0) {
                growthRate = Double.parseDouble(etxGrowthRate.getText().toString());
                if (growthRate >= 1) { // user may enter the percentage
                    growthRate = (double) growthRate / 100;
                }
            }

            if (etxCount != null && etxCount.getText() != null && etxCount.getText().length() > 0) {
                count = Integer.parseInt(etxCount.getText().toString());
            }

            if (initVal != null && regularContribution != null && growthRate != null && count != null) {
                latteFactor = new LatteFactor(initVal, regularContribution, growthRate);
                txResult.setText(String.valueOf(LatteFactor.formatDecimalValue(latteFactor.calculate(count))));
            }
            else {
//            txResult.setText(msg);
//            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//            builder.setTitle(getText(R.string.warning));
//            builder.setMessage(msg);
//            AlertDialog dialog = builder.create();
////            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG);
////            dialog.setTitle(getText(R.string.warning));
////            dialog.setMessage(getText(R.string.missing_inputs));
//            dialog.show();
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        }
        catch (NumberFormatException nfe) {
            // txResult.setText("NumberFormatException caught: " + nfe.getMessage());
            Toast.makeText(getApplicationContext(), "NumberFormatException caught: " + nfe.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void clear(View v) {
        if (etxInitialValue != null) {
            etxInitialValue.setText("");
        }

        if (etxRegularContribution != null) {
            etxRegularContribution.setText("");
        }

        if (etxGrowthRate != null) {
            etxGrowthRate.setText("");
        }

        if (etxCount != null) {
            etxCount.setText("");
        }

        if (txResult != null) {
            txResult.setText("");
        }
    }
}
