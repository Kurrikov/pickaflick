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
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.apptik.widget.MultiSlider;

public class MovieRating extends AppCompatActivity {
    ProgressBar bar = null;
    Bundle bundle;
    Intent intent;
    float scoreMin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_rating);

        bundle = getIntent().getExtras();

        // Set loading indicator as invisible when this view is initially loaded
        bar = (ProgressBar) findViewById(R.id.progressBar);
        bar.setVisibility(View.INVISIBLE);

        // for debugging era input:
//        bundle = getIntent().getExtras();
//        TextView mpaaDebug = (TextView) findViewById(R.id.mpaaDebug);
//        mpaaDebug.setText("The maximum MPAA rating is " + bundle.getString("mpaaratings"));


        final TextView minScoreView = (TextView) findViewById(R.id.minScoreView);
        MultiSlider scoreSlider = (MultiSlider) findViewById(R.id.score_slider);
        scoreSlider.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
                scoreMin = (float) value / 2;
                minScoreView.setText("Minimum rating: " + String.valueOf(scoreMin));
            }
        });

        // Continue button for moving to the next activity
        Button continueButton = (Button) findViewById(R.id.continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putString("minScore", String.valueOf(scoreMin));
                // Start loading indicator
                bar.setVisibility(View.VISIBLE);

                new FetchMoviesTask().execute((URL) null);
            }
        });
    }


    /**
     *  THIS IS THE METHOD THAT YOU MUST USE, after you have gotten all of your filters. You must make sure to use try-catch blocks for the exceptions. The only serious exception that will require actual handling is the no-Internet exception. In the test-method I have demonstrated its usage
     * @param mpaa_Ratings: A String from the following list: E,G,PG,M,MA15+,R18+,X18+,RC,14A,18A,R,A,NR,PG-13,NC-17 ... . NOTE: The MPAA rating will return movies with that rating AND lower. So if the filter is R, you will also get movies that are PG, E, etc. See http://docs.themoviedb.apiary.io/#reference/certifications/certificationmovielist/get for the details and full list.
     * @param genre: A String from the following list: Adventure, Animation, Comedy, Crime, Documentary, Drama, Family, Fantasy, Foreign, History, Horror, Music, Mystery, Romance, Science Fiction, TV Movie, Thriller, War, Western. The same string cannot be used twice or more.
     * @param era_low: A 4 digit integer indicating the a year at which the movie(s) was/were released after. Ranges from 1900 to 2090. For example, movies from the 2000s will need the integer 2000. Movies from the 1980s will need the integer 1980, and so on. (Don't try years that don't end with a 0)
     * @param era_high: A 4 digit integer indicating the a year at which the movie(s) was/were released before. Ranges from 1900 to 2090.
     * @param minRating: A float from 0.0 to 10.0. This will be the minimum rating in the list of movies that you get. So if it is 8.0, you will get movies whose ratings range from 8 to 10.
     * @return A list containing movie attributes of all filtered movies. See the MovieAttribute.java class for details.
     * @throws TmdbExecutor.NoInternetConnectionException: If there is no internet or very slow internet. See TmdbRunnable's hostIsReachable() method. You probably just want to display the message from the exception in the catch block. See the test-method below.
     * @throws InterruptedException: if the image(s) fail to download. No particular handling should be needed for this. Just use an empty catch block.
     */
    protected List<MovieAttributes> fetchMoviesList(String mpaa_Ratings, String genre, String era_low, String era_high, String minRating) throws TmdbExecutor.NoInternetConnectionException, InterruptedException{
        final String API_ID = "d2148dac5e85b1dee3f0fe5e2c3a83ab";
        TmdbMoviesObject tmdbMoviesobject = new TmdbMoviesObject(mpaa_Ratings, genre, era_low, era_high, minRating);
        new TmdbExecutor(tmdbMoviesobject, API_ID).execute();
        if(tmdbMoviesobject.getMoviesList()== null){
            throw new TmdbExecutor.NoInternetConnectionException();
        }
        else return tmdbMoviesobject.getMoviesList();
    }


    //class to handle background list generation so that the UI is freed up
    private class FetchMoviesTask extends AsyncTask<URL, Integer, Long> {

        protected Long doInBackground(URL... urls) {

                intent = new Intent(MovieRating.this, MovieListActivity.class);

                //Retrieve movie list and store in bundle
                List<MovieAttributes> movieList;
                ArrayList<String> movieTitles = new ArrayList();
                ArrayList<String> movieUrls = new ArrayList();

                try {
                    movieList = fetchMoviesList(bundle.getString("mpaaratings"), bundle.getString("genre"), bundle.getString("era_low"), bundle.getString("era_high"), bundle.getString("minScore"));
                    for(MovieAttributes item : movieList)
                    {
                        movieTitles.add(item.getTitle());
                        movieUrls.add(item.getTmdbPageURL());
                    }
                    bundle.putStringArrayList("movieTitles", movieTitles);
                    bundle.putStringArrayList("movieUrls", movieUrls);
                    bundle.putString("Internet Exception", "");
                    bundle.putString("Interruption Exception", "");

                } catch (TmdbExecutor.NoInternetConnectionException e) {
                    bundle.putString("Internet Exception", e.getMessage());
                    bundle.putString("Interruption Exception", "");
                } catch (InterruptedException e) {      //this will happen only if the network thread is interrupted for some reason.
                    bundle.putString("Interruption Exception", e.getMessage());
                    bundle.putString("Internet Exception", "");
                }
                intent.putExtras(bundle);
            return 0L;
        }

        protected void onProgressUpdate(Integer... progress) {}

        protected void onPostExecute(Long result) {
            bar.setVisibility(View.INVISIBLE);
            // Go to MovieListActivity
            startActivity(intent);
        }
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
