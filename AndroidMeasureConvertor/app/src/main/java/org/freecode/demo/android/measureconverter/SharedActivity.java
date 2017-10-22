package org.freecode.demo.android.measureconverter;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by tyler on 2017-08-31.
 */

public abstract class SharedActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.miTemperature:
                startActivityByClass(TemperatureActivity.class);
                // Toast.makeText(getApplicationContext(),"Temperature Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.miLength:
                startActivityByClass(LengthActivity.class);
                return true;
            case R.id.miWeight:
                startActivityByClass(WeightActivity.class);
                return true;
            case R.id.miVolume:
                startActivityByClass(VolumeActivity.class);
                return true;
            case R.id.miArea:
                startActivityByClass(AreaActivity.class);
                return true;
            case R.id.miWikiURL:
                loadWikiPage();
                return true;
            case R.id.miExit:
                finish(); // finish the current activity
                finishMainActivity();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishMainActivity();
    }

    private void finishMainActivity() {
        if (MainActivity.theInstance != null) {
            MainActivity.theInstance.finish();
        }
        System.exit(0);
    }

    private void startActivityByClass(Class aClass)
    {
        Intent intent = new Intent(this, aClass);
        // after API v4 since singleTask can be configured in the Android manifest
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // clear other activities
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    /**
     * close the activity
     */
    public void closeMainActivity() {

    }

    /**
     * open an external browser for a Wiki pagee
     */
    public void loadWikiPage() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String wikiURL = "http://en.wikipedia.org/wiki/Conversion_of_units";
        intent.setData(Uri.parse(wikiURL));
        startActivity(intent);
    }

    public abstract void clearFields(View v);
    public abstract void calculate(View v);
}
