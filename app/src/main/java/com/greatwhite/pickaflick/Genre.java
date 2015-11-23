package com.greatwhite.pickaflick;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Genre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

        final Intent intent = new Intent(Genre.this, SubGenre.class);

        final Bundle bundle = new Bundle();

        Button button3= (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Place string in bundle
                bundle.putString("genre", "Action");
                intent.putExtras(bundle);

                //Start Next Activity
                startActivity(intent);
            }
        });

        Button button4= (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Place string in bundle
                bundle.putString("genre", "Comedy");
                intent.putExtras(bundle);

                //Start Next Activity
                startActivity(intent);            }
        });

        Button button5= (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Place string in bundle
                bundle.putString("genre", "Western");
                intent.putExtras(bundle);

                //Start Next Activity
                startActivity(intent);
            }
        });

        Button button6= (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Place string in bundle
                bundle.putString("genre", "Comedy");
                intent.putExtras(bundle);

                //Start Next Activity
                startActivity(intent);
            }
        });

        Button button7= (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Place string in bundle
                bundle.putString("genre", "Crime");
                intent.putExtras(bundle);

                //Start Next Activity
                startActivity(intent);
            }
        });

        Button button8= (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Place string in bundle
                bundle.putString("genre", "Documentary");
                intent.putExtras(bundle);

                //Start Next Activity
                startActivity(intent);
            }
        });

        Button button9= (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Place string in bundle
                bundle.putString("genre", "Drama");
                intent.putExtras(bundle);

                //Start Next Activity
                startActivity(intent);
            }
        });

        Button button10= (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Place string in bundle
                bundle.putString("genre", "Family");
                intent.putExtras(bundle);

                //Start Next Activity
                startActivity(intent);
            }
        });

        Button button11= (Button) findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Place string in bundle
                bundle.putString("genre", "Fantasy");
                intent.putExtras(bundle);

                //Start Next Activity
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_genre, menu);
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
