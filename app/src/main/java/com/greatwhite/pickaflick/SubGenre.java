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

//This entire class is essentially a copy of Genre.java, with name and reference changes.


public class SubGenre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_genre);

        final Intent intent = new Intent(SubGenre.this, Era.class);

        final Bundle bundle = getIntent().getExtras();

        TextView myTextView = (TextView) findViewById(R.id.textView6);
        myTextView.setText("The genre is " + bundle.getString("genre"));

        Button button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //add to bundle and continue passing bundle to next activity
                bundle.putString("subgenre", "Action");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        Button button4= (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putString("subgenre", "Comedy");
                intent.putExtras(bundle);

                startActivity(intent);            }
        });

        Button button5= (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putString("subgenre", "Western");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        Button button6= (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putString("subgenre", "Comedy");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        Button button7= (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putString("subgenre", "Crime");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        Button button8= (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putString("subgenre", "Documentary");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        Button button9= (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putString("subgenre", "Drama");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        Button button10= (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putString("subgenre", "Family");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        Button button11= (Button) findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putString("subgenre", "Fantasy");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub_genre, menu);
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
