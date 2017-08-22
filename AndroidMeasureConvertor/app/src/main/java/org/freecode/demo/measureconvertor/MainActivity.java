package org.freecode.demo.measureconvertor;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * start the corresponding activity based on a selected category
     *
     * @param v
     */
    public void loadMeasureCategory(View v) {
        boolean isChecked = ((RadioButton) v).isChecked();
        Intent intent = null;

        switch (v.getId()) {
            case R.id.rbTemperature:
                if (isChecked) {
                    intent = new Intent(this, TemperatureActivity.class);
                }
                break;
            case R.id.rbLength:
                if (isChecked) {
                    intent = new Intent(this, LengthActivity.class);
                }
                break;
            case R.id.rbWeight:
                if (isChecked) {
                    intent = new Intent(this, WeightActivity.class);
                }
                break;
            case R.id.rbVolume:
                if (isChecked) {
                    intent = new Intent(this, VolumeActivity.class);
                }
                break;
            case R.id.rbArea:
                if (isChecked) {
                    intent = new Intent(this, AreaActivity.class);
                }
                break;
            default:
                Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT).show();
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

    /**
     * open an external browser for a Wiki page
     *
     * @param v
     */
    public void loadWikiPage(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String wikiURL = "http://en.wikipedia.org/wiki/Conversion_of_units";
        intent.setData(Uri.parse(wikiURL));
        startActivity(intent);
    }

}
