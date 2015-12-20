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

    /**
     * Set moviesList to the specified list of movies.
     * @param movieDbs A list of movies
     */
    public void setMoviesList(List<MovieDb> movieDbs){
        if(movieDbs != null){
            moviesList = new ArrayList<MovieAttributes>();
            int minSize = Math.min(movieDbs.size(), 20);
            for (int i = 0; i < minSize; i++) {
                this.moviesList.add(new MovieAttributes(movieDbs.get(i)));
            }
        }
    }

    public List<MovieAttributes> getMoviesList() {
        return this.moviesList;
    }

    /**
     * The default TMDb method does a poor job in filtering movies by years of release, so this method was added to rectify that issue.
     * This will also prioritize the order based on voteCounts and vote Averages (the Ratings) combined. This will hopefully manage
     * to filter out lower quality movies that made their way into the list, such as those with too few votes to be considered legitimate
     * @param movieDbs A list of movies
     * @return A corrected list of movies
     */
    public List<MovieDb> listCorrector(List<MovieDb> movieDbs) {

        //remove "out of date" movies
        for (int i = 0; i < movieDbs.size(); ) {
            int releaseYear = Integer.parseInt(movieDbs.get(i).getReleaseDate().substring(0, 4));
            if ((releaseYear > era_high || releaseYear < era_low)) movieDbs.remove(i);
            else i++;
        }

        //Sort by vote count and rating (descending order)
        Collections.sort(movieDbs, new Comparator<MovieDb>() {
        // In order to sort this list, we need a comparator that can compare the ratings and vote counts to each other.
        // (There is way more than one way to do this.)
            @Override
            public int compare(MovieDb movieDb1, MovieDb movieDb2) {
                return (int)(movieDb2.getVoteAverage()*Math.log10(movieDb2.getVoteCount()) - (movieDb1.getVoteAverage()*Math.log10(movieDb1.getVoteCount())));
            }

        });

        return movieDbs;
    }

}