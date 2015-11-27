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

import com.greatwhite.pickaflick.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;


public class MovieRating extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_rating);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onButtonClick("9.0");
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onButtonClick("7.5");           }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onButtonClick("5.0");
            }
        });
    }

    private void onButtonClick(String rating) {
        final Intent intent = new Intent(MovieRating.this, MovieListActivity.class);

        final Bundle bundle = getIntent().getExtras();


        bundle.putString("movierating", rating);

        //Start loading indicator

        //Retrieve movie list and store in bundle
        List<MovieAttributes> movieList = null;
        ArrayList<String> movieTitles = new ArrayList();
        ArrayList<String> movieUrls = new ArrayList();


        try {
            movieList = fetchMoviesList(bundle.getString("mpaaratings"), bundle.getString("genre"), bundle.getString("era"), bundle.getString("movierating"));
            for(MovieAttributes item : movieList)
            {
                movieTitles.add(item.getTitle());
                movieUrls.add(item.getTmdbPageURL());
            }
            bundle.putStringArrayList("movieTitles", movieTitles);
            bundle.putStringArrayList("movieUrls", movieUrls);
            intent.putExtras(bundle);
       } catch (TmdbRunnable.NoInternetConnectionException e) {
//            ErrorMessage = e.getMessage();
        } catch (InterruptedException e) {      //this will happen only if the network thread is interrupted for some reason.
//            ErrorMessage = e.getMessage();
        }
        //Turn off loading indicator

        startActivity(intent);
    }

    protected List<MovieAttributes> fetchMoviesList(String mpaa_Ratings, String genre, String era, String minRating) throws TmdbRunnable.NoInternetConnectionException, InterruptedException{
        final String API_ID = "d2148dac5e85b1dee3f0fe5e2c3a83ab";
        TmdbMoviesObject tmdbMoviesobject = new TmdbMoviesObject(mpaa_Ratings, genre, era, minRating);
        Thread thread = new Thread(new TmdbRunnable(tmdbMoviesobject, API_ID)); //all the network calls for image retrieval and movie list retrieval will be done on this thread
        thread.start();
        thread.join();
        if(tmdbMoviesobject.getMoviesList()== null){
            throw new TmdbRunnable.NoInternetConnectionException();
        }
        else return tmdbMoviesobject.getMoviesList();
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
