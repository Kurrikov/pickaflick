package com.example.keon.dynamicapplication1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.graphics.Color;
import android.widget.EditText;
import android.content.res.Resources;
import android.util.TypedValue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        RelativeLayout keonsLayout = new RelativeLayout(this);

        Button redButton = new Button(this);
        redButton.setId('1');

        EditText username = new EditText(this);
        username.setId('2');

        //defines parameters for a generic layout, which we shall associate with our layout below
        RelativeLayout.LayoutParams buttonDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,       //adjust height and width so that it wraps everything
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        //user


        RelativeLayout.LayoutParams usernameDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,       //adjust height and width so that it wraps everything
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        usernameDetails.addRule(RelativeLayout.ABOVE, redButton.getId()); //put it above red button
        usernameDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);  //put it in the center of the row which it is in
        usernameDetails.setMargins(0, 0, 0, 50); //these values are relative to the where it would've been

        //now add some rules to the layout params that go into the layout
        buttonDetails.addRule(RelativeLayout.CENTER_HORIZONTAL); //our layout params should have the rule of being at the center for whatever purpose we may need. this rule should be associated with everything we add to our layout until we change it.
        buttonDetails.addRule(RelativeLayout.CENTER_VERTICAL);

        //THIS WILL NOW STANDARDIZE Device Pixels on all devices
        Resources r = getResources(); //gets all the info relevant to this code
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());
        username.setWidth(px);


        keonsLayout.setBackgroundColor(Color.GREEN);

        redButton.setText("Log In");
        redButton.setBackgroundColor(Color.RED);

        keonsLayout.addView(username, usernameDetails);
        keonsLayout.addView(redButton, buttonDetails); //adds the button to this layout, and uses the rules of buttondetails to place it at the right place

        setContentView(keonsLayout); //makes keonslayout the main screen




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
