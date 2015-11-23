package com.example.keon.interactiveapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView keonsText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button keonsButton = (Button)findViewById(R.id.keonsButton);

        keonsButton.setOnClickListener(
                new Button.OnClickListener(){   //this is an interface
                    public void onClick(View v){
                       keonsText = (TextView) findViewById(R.id.keonsText); //fetches the text in the layout
                        if(keonsText.getText().toString() == "Good Job"){
                            keonsText.setText("Excellent Job");
                        }
                        else{
                        keonsText.setText("Good Job");  //sets the text in the layout}
                        }
                    }
                }
        );


        keonsButton.setOnLongClickListener(
            new Button.OnLongClickListener(){
                public boolean onLongClick(View v) {
                    keonsText = (TextView) findViewById(R.id.keonsText);
                    keonsText.setText("Long Click!");
                    return true;
                    //we need this return statement, because it is going
                    //to check if the button is pressed long enough (> 1s). if not it
                    //will hand down the event to onclicklistener
                }
            }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
