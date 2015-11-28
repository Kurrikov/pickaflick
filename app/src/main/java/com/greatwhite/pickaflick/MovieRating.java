package com.greatwhite.pickaflick;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieRating extends AppCompatActivity {
    ProgressBar bar = null;
    Bundle bundle;
    Intent intent;
    String rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_rating);

        //Turn off loading indicator
        bar = (ProgressBar) findViewById(R.id.progressBar);
        bar.setVisibility(View.INVISIBLE);

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
        //Start loading indicator
        bar.setVisibility(View.VISIBLE);
        this.rating = rating;
        new FetchMoviesTask().execute((URL)null);
    }

    protected List<MovieAttributes> fetchMoviesList(String mpaa_Ratings, String genre, String era, String minRating) throws TmdbExecutor.NoInternetConnectionException, InterruptedException{
        final String API_ID = "d2148dac5e85b1dee3f0fe5e2c3a83ab";
        TmdbMoviesObject tmdbMoviesobject = new TmdbMoviesObject(mpaa_Ratings, genre, era, minRating);
        new TmdbExecutor(tmdbMoviesobject, API_ID).execute();

        if(tmdbMoviesobject.getMoviesList()== null){
            throw new TmdbExecutor.NoInternetConnectionException();
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

    //class to handle background list generation so that the UI is freed up
    private class FetchMoviesTask extends AsyncTask<URL, Integer, Long> {
        protected Long doInBackground(URL... urls) {
            bundle = getIntent().getExtras();
            intent = new Intent(MovieRating.this, MovieListActivity.class);

            bundle.putString("movierating", rating);

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
            } catch (TmdbExecutor.NoInternetConnectionException e) {
//            ErrorMessage = e.getMessage();
            } catch (InterruptedException e) {      //this will happen only if the network thread is interrupted for some reason.
//            ErrorMessage = e.getMessage();
            }

            return 0L;
        }

        protected void onProgressUpdate(Integer... progress) {
       }

        protected void onPostExecute(Long result) {
            bar.setVisibility(View.INVISIBLE);

            // Go to MovieListActivity
            startActivity(intent);
        }
    }

}
