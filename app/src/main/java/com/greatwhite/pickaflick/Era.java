package com.greatwhite.pickaflick;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.apptik.widget.MultiSlider;


public class Era extends ActionBarActivity {

    String era_low = "1900";
    String era_high = "2020";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_era);

        final Intent intent = new Intent(Era.this, MpaaRatings.class);
        final Bundle bundle = getIntent().getExtras();

        // TextView for debugging
        TextView myTextView = (TextView) findViewById(R.id.textView5);
        myTextView.setText("The genre is " + bundle.getString("genre"));

        final TextView minView = (TextView) findViewById(R.id.minView);
        final TextView maxView = (TextView) findViewById(R.id.maxView);

        MultiSlider eraSlider = (MultiSlider) findViewById(R.id.range_slider);
        eraSlider.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
                TextView myTextView = (TextView) findViewById(R.id.textView5);
                if (thumbIndex == 0) {
                    era_low = String.valueOf(value);
                    minView.setText("Minimum year: " + String.valueOf(value));
                }
                else {
                    era_high = String.valueOf(value);
                    maxView.setText("Maximum year: " + String.valueOf(value));
                }
            }
        });



        // Continue button for moving to the next activity
        Button continue_button = (Button) findViewById(R.id.continue_button);
        continue_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putString("era_low", era_low);
                bundle.putString("era_high", era_high);

                intent.putExtras(bundle);

                //Start Next Activity
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_era, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}