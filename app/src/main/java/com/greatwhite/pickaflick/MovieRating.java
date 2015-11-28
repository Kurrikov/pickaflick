package com.greatwhite.pickaflick;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.apptik.widget.MultiSlider;


public class MovieRating extends AppCompatActivity {
    float scoreMax = 10;
    float scoreMin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_rating);

        final Intent intent = new Intent(MovieRating.this, MovieDetailActivity.class);

        final Bundle bundle = getIntent().getExtras();

        TextView myTextView = (TextView) findViewById(R.id.textView7);
        myTextView.setText("The mpaarating is " + bundle.getString("mpaaratings"));

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //add to bundle and continue passing bundle to next activity
                bundle.putString("movierating", "9.0");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putString("movierating", "7.5");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putString("movierating", "6.0");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });


        final TextView minView = (TextView) findViewById(R.id.minScoreView);
        final TextView maxView = (TextView) findViewById(R.id.maxScoreView);
        MultiSlider scoreSlider = (MultiSlider) findViewById(R.id.score_slider);
        scoreSlider.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
                TextView myTextView = (TextView) findViewById(R.id.textView5);
                if (thumbIndex == 0) {
                    scoreMin = (float) value/2;
                    minView.setText("Minimum rating: " + String.valueOf(scoreMin));
                }
                else {
                    scoreMax = (float) value/2;
                    if(scoreMax < 10){
                        maxView.setText("Maximum rating:   " + String.valueOf(scoreMax));
                    }
                    else{
                        maxView.setText("Maximum rating: " + String.valueOf(scoreMax));
                    }
                }
            }
        });

        // Continue button for moving to the next activity
        Button continue_button = (Button) findViewById(R.id.continue_button);
        continue_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putString("movierating", String.valueOf(scoreMin));
                bundle.putString("movierating", String.valueOf(scoreMax));
                intent.putExtras(bundle);

                //Start Next Activity
                startActivity(intent);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_rating, menu);
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
