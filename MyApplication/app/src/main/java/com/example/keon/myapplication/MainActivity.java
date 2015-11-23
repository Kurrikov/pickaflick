package com.example.keon.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.util.Log; //lets us print log info for tracking purposes.
//alt + ins => code generator

public class MainActivity extends AppCompatActivity {

    private static final String BUCKY_TAG = "buckysMessage";

    //AppCompatActivity inherits from Activity
    //OnCreate is like the constructor of this class ; initiated first.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(BUCKY_TAG, "onCreate");  //BUCKY_BUCKY_TAG is going to act like a filter (it's an identifier)
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(BUCKY_TAG, "onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(BUCKY_TAG, "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i(BUCKY_TAG, "onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i(BUCKY_TAG, "onStop");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(BUCKY_TAG, "onRestart");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(BUCKY_TAG, "onDestroy");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(BUCKY_TAG, "onSaveInstanceState");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(BUCKY_TAG, "onRestoreInstanceState");
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
