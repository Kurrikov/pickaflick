package com.greatwhite.pickaflick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MpaaRatings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpaa_ratings);

        final Intent intent = new Intent(MpaaRatings.this, MovieRating.class);
        final Bundle bundle = getIntent().getExtras();

        // for debugging era input:
        TextView eraDebug = (TextView) findViewById(R.id.eraDebug);
        eraDebug.setText("The minimum year is " + bundle.getString("era_low") + "\nThe maximum year is " + bundle.getString("era_high"));

        Button RButton = (Button) findViewById(R.id.R_button);
        RButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //add to bundle and continue passing bundle to next activity
                bundle.putString("mpaaratings", "R");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        Button PG13Button = (Button) findViewById(R.id.PG13_button);
        PG13Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putString("mpaaratings", "PG-13");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        Button PGButton = (Button) findViewById(R.id.PG_button);
        PGButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putString("mpaaratings", "PG");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        Button GButton = (Button) findViewById(R.id.G_button);
        GButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putString("mpaaratings", "G");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mpaa_ratings, menu);
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
