package com.greatwhite.pickaflick;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import info.movito.themoviedbapi.model.MovieDb;

/**
 * Created by Keon on 11/8/2015.
 * Modified by Jason on 2015-11-27 -- Added handling for low and high limits for era
 */
public class TmdbMoviesObject extends TmdbObject {

    private List<MovieAttributes> moviesList;

    public TmdbMoviesObject(String MPAA_Rating, String genre, String era_low, String era_high, String minRating){
        super(MPAA_Rating, genre, era_low, era_high, minRating);
    }

    public void setMoviesList(List<MovieDb> movieDbs){
        if(movieDbs != null){
            moviesList = new ArrayList<MovieAttributes>();
            int minSize = minimum(movieDbs.size(),20);
            for (int i = 0; i < minSize; i++) {
                this.moviesList.add(new MovieAttributes(movieDbs.get(i)));
            }
        }
    }

    private int minimum(int x, int y){
        return x > y ? y : x;
    }

    public List<MovieAttributes> getMoviesList() {
        return this.moviesList;
    }

    //the default tmdb method does a poor job in filtering movies by years of release, so this method was added to rectify that issue
    //this will also prioritize the order based on voteCounts and vote Averages (the Ratings) combined. This will hopefully manage to filter out lower quality
    // movies that made their way into the list, such as those with too few votes to be considered legitimate
    public List<MovieDb> listCorrector(List<MovieDb> movieDbs) {

        //remove "out of date" movies
        for (int i = 0; i < movieDbs.size(); ) {
            int releaseYear = Integer.parseInt(movieDbs.get(i).getReleaseDate().substring(0, 4));
            if ((releaseYear > era_high || releaseYear < era_low)) movieDbs.remove(i);
            else i++;
        }

        //Sort by vote count and rating (descending order)
        Collections.sort(movieDbs, new Comparator<MovieDb>() {      //in order to sort this list, we need a comparator that can compare the ratings and vote counts to each other.
                                                                    // (There is way more than one way to do this.)
            @Override
            public int compare(MovieDb movieDb1, MovieDb movieDb2) {
                return (int)(movieDb2.getVoteAverage()*Math.log10(movieDb2.getVoteCount()) - (movieDb1.getVoteAverage()*Math.log10(movieDb1.getVoteCount())));
            }

        });

        return movieDbs;
    }

}