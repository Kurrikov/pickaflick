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
            movieList = fetchMoviesList(bundle.getString("mpaaratings"), bundle.getString("genre"), bundle.getString("era_low"), bundle.getString("era_high"), bundle.getString("movierating"));
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
        String s = "";
        if(movieList != null && movieList.size() != 0){
            imageView = movieList.get(0).getImageView(imageView, 200, 200);    //this is how you can get the movie poster (in this case, for the 0th movie in the list).
            for(int i = 0; i < movieList.size(); i++)
                s = s + movieList.get(i).getTitle() + " (" + movieList.get(i).getReleaseDate().substring(0,4) + ") ; Rating: "  + movieList.get(i).getRatings() + "/10 ; " + "VC: " + movieList.get(i).getVoteCount() + "\n"; //print the title and release dates of the movies
        }
        else{
            s = "No movies to display";
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

    /**
     *  THIS IS THE METHOD THAT YOU MUST USE, after you have gotten all of your filters. You must make sure to use try-catch blocks for the exceptions. The only serious exception that will require actual handling is the no-Internet exception. In the test-method I have demonstrated its usage
     * @param mpaa_Ratings: A String from the following list: E,G,PG,M,MA15+,R18+,X18+,RC,14A,18A,R,A,NR,PG-13,NC-17 ... . NOTE: The MPAA rating will return movies with that rating AND lower. So if the filter is R, you will also get movies that are PG, E, etc. See http://docs.themoviedb.apiary.io/#reference/certifications/certificationmovielist/get for the details and full list.
     * @param genre: A String from the following list: Adventure, Animation, Comedy, Crime, Documentary, Drama, Family, Fantasy, Foreign, History, Horror, Music, Mystery, Romance, Science Fiction, TV Movie, Thriller, War, Western. The same string cannot be used twice or more.
     * @param era_low: A 4 digit integer indicating the decade at which the movie(s) was/were released. Ranges from 1900 to 2090. For example, movies from the 2000s will need the integer 2000. Movies from the 1980s will need the integer 1980, and so on. (Don't try years that don't end with a 0)
     * @param era_high: A 4 digit integer indicating an upper limit for the decade that the movie(s) was/were released.
     * @param minRating: A float from 0.0 to 10.0. This will be the minimum rating in the list of movies that you get. So if it is 8.0, you will get movies whose ratings range from 8 to 10.
     * @return A list containing movie attributes of all filtered movies. See the MovieAttribute.java class for details.
     * @throws TmdbRunnable.NoInternetConnectionException: If there is no internet or very slow internet. See TmdbRunnable's hostIsReachable() method. You probably just want to display the message from the exception in the catch block. See the test-method below.
     * @throws InterruptedException: if the image(s) fail to download. No particular handling should be needed for this. Just use an empty catch block.
     */
    protected List<MovieAttributes> fetchMoviesList(String mpaa_Ratings, String genre, String era_low, String era_high, String minRating) throws TmdbRunnable.NoInternetConnectionException, InterruptedException{
        final String API_ID = "d2148dac5e85b1dee3f0fe5e2c3a83ab";
        TmdbMoviesObject tmdbMoviesobject = new TmdbMoviesObject(mpaa_Ratings, genre, era_low, era_high, minRating);
        Thread thread = new Thread(new TmdbRunnable(tmdbMoviesobject, API_ID)); //all the network calls for image retrieval and movie list retrieval will be done on this thread
        thread.start();
        thread.join();
        if(tmdbMoviesobject.getMoviesList()== null){
            throw new TmdbRunnable.NoInternetConnectionException();
        }
        else return tmdbMoviesobject.getMoviesList();
    }
}
