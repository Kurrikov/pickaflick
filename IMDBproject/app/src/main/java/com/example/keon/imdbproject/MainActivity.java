package com.example.keon.imdbproject;


import android.graphics.Movie;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.config.TmdbConfiguration;
import info.movito.themoviedbapi.TmdbApi;

public class MainActivity extends AppCompatActivity {

    private static final String API_ID = "d2148dac5e85b1dee3f0fe5e2c3a83ab";

    TmdbMoviesObject tmdbMoviesobject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textview = (TextView) findViewById(R.id.textView);

        //test

        try {
            textview.setText(fetchMovies("E","Comedy",2000,8));
        } catch (TmdbObject.GenreException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param mpaa_Ratings: A String from the following list: E,G,PG,M,MA15+,R18+,X18+,RC,14A,18A,R,A,NR,PG-13,NC-17
     * @param genre: A String from the following list: Adventure, Animation, Comedy, Crime, Documentary, Drama, Family, Fantasy, Foreign, History, Horror, Music, Mystery, Romance, Science Fiction, TV Movie, Thriller, War, Western
     * @param era: A 4 digit integer indicating the decade at which the movie(s) was/were released. Ranges from 1900 to 2090. For example, movies from the 2000s will need the integer 2000. Movies from the 1980s will need the integer 1980, and so on. (Don't try years that don't end with a 0)
     * @param minRating: A float from 0.0 to 10.0. This will be the minimum rating in the list of movies that you get. So if it is 8.0, you will get movies whose ratings range from 8 to 10.
     * @return
     * @throws TmdbObject.GenreException: If the supplied genre doesn't exist this is the error you get. This shouldn't be an issue for this project.
     * @throws InterruptedException: If the thread connecting to the tmdb server is interrupted this is thrown.
     */
    protected String fetchMovies(String mpaa_Ratings, String genre, int era, float minRating) throws TmdbObject.GenreException, InterruptedException{

        tmdbMoviesobject = new TmdbMoviesObject(mpaa_Ratings, genre, era, minRating);
        String s = "";

        Thread thread = new Thread(new TmdbRunnable(tmdbMoviesobject, API_ID));

        thread.start();
        thread.join();

        tmdbMoviesobject.createMoviesList();

        //test code -- print the title and release dates of the movies
        List<MovieAttributes> list = tmdbMoviesobject.getMoviesList();
        if(list != null){
            for(int i = 0; i < list.size(); i++)
            s = s + list.get(i).getTitle() + " (" + list.get(i).getReleaseDate().substring(0,4) + ")\n";
        }

        return s;
    }





}

