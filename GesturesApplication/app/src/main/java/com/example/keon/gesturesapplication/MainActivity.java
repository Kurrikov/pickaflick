package com.example.keon.gesturesapplication;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
GestureDetector.OnDoubleTapListener, OnClickListener {

    private TextView keonsMessage;
    private Button keonsButton;
    private Button buckysButton;
    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keonsButton = (Button) findViewById(R.id.button);
        buckysButton = (Button) findViewById(R.id.button2);
        keonsMessage = (TextView) findViewById(R.id.keonsMessage);

        keonsButton.setOnClickListener((OnClickListener) this);
        buckysButton.setOnClickListener((OnClickListener) this);

        this.gestureDetector = new GestureDetectorCompat(this,this);
        gestureDetector.setOnDoubleTapListener(this);

    }

    public void onClick(View v){
        Resources res = v.getResources();
        String s = res.getResourceEntryName(v.getId());

        if(s.equals("button")){
            keonsMessage.setText("Keon's Button Clicked");
        }
        else if(s.equals("button2")){
            keonsMessage.setText("Bucky's Button Clicked");
        }
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        keonsMessage.setText("single tap");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        keonsMessage.setText("double tap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        keonsMessage.setText("double tap event");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        keonsMessage.setText("showpress");
    }

    @Override
    public boolean onDown(MotionEvent e) {
        keonsMessage.setText("down");
        return true;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        keonsMessage.setText("single tap up");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        keonsMessage.setText("long press");
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        keonsMessage.setText("Scrolling");
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        keonsMessage.setText("fling");
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /////////////////////////END OF GESTURES/////////////////////////////////


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);  //makes sure that the event was a gesture
        return super.onTouchEvent(event);
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
