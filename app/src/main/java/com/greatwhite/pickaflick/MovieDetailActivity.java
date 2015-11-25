package com.greatwhite.pickaflick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * An activity representing a single Movie detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link MovieListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link MovieDetailFragment}.
 */
public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Bundle bundle = getIntent().getExtras();



        TextView myTextView2 = (TextView) findViewById(R.id.textView4);
        //myTextView2.setText("The era is " + bundle.getString("era"));
        /*
        TextView myTextView = (TextView) findViewById(R.id.textView2);
        myTextView.setText("The genre is " + bundle.getString("genre"));

        TextView myTextView3 = (TextView) findViewById(R.id.textView5);
        myTextView3.setText("The mpaaratings is " + bundle.getString("mpaaratings"));

        TextView myTextView4 = (TextView) findViewById(R.id.textView6);
        myTextView4.setText("The movierating is " + bundle.getString("movierating"));
        */


        String ErrorMessage = "";  //if for whatever reason the fetchMoviesList() method fails, this message will contain the error information.
        List<MovieAttributes> movieList;
        try {
            movieList = fetchMoviesList(bundle.getString("mpaaratings"), bundle.getString("genre"), bundle.getString("era"), bundle.getString("movierating"));
            temporaryMovieDisplayer(myTextView2, movieList);
        } catch (TmdbRunnable.NoInternetConnectionException e) {
            ErrorMessage = e.getMessage();
        } catch (InterruptedException e) {      //this will happen only if the network thread is interrupted for some reason.
            ErrorMessage = e.getMessage();
        }

        if(!ErrorMessage.equals("")) myTextView2.setText(ErrorMessage);     //display the error


        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(MovieDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(MovieDetailFragment.ARG_ITEM_ID));
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit();
        }
    }

    //a quick method to show some of the features of the movieAttributes Class
    protected void temporaryMovieDisplayer(TextView textView, List<MovieAttributes> movieList){
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
                                                                            // You may change its size by passing in two additional int parameters
        String s = "No Movies To display";
        if(movieList != null && movieList.size() != 0){
            imageView = movieList.get(0).getImageView(imageView, 400, 400);    //this is how you can get the movie poster (in this case, for the 0th movie in the list).
            for(int i = 0; i < movieList.size(); i++)
                s = s + movieList.get(i).getTitle() + " (" + movieList.get(i).getReleaseDate().substring(0,4) + ")\n"; //print the title and release dates of the movies
        }
        textView.setText(s);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, MovieListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
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
}
