package com.example.keon.imdbproject;

import java.util.ArrayList;
import java.util.List;
import info.movito.themoviedbapi.model.MovieDb;

/**
 * Created by Keon on 11/8/2015.
 */
public class TmdbMoviesObject extends TmdbObject {

    private List<MovieAttributes> moviesList;

    public TmdbMoviesObject(String MPAA_Rating, String genre, String subgenre, int era, float minRating) throws GenreException {
        super(MPAA_Rating, genre, subgenre, era, minRating);
    }

    public void setMoviesList(List<MovieDb> movieDbs){
        if(movieDbs != null){
            moviesList = new ArrayList<MovieAttributes>();
            for (int i = 0; i < movieDbs.size(); i++) {
                this.moviesList.add(new MovieAttributes(movieDbs.get(i)));
            }
        }
    }

    public List<MovieAttributes> getMoviesList() {
        return this.moviesList;
    }

    //the default tmdb method does a poor job in filtering movies by years of release, so this method was added to rectify that issue
    public List<MovieDb> listCorrector(List<MovieDb> movieDbs) {
        for (int i = 0; i < movieDbs.size(); i++) {
            int releaseYear = Integer.parseInt(movieDbs.get(i).getReleaseDate().substring(0, 4));
            if (releaseYear > era + 9 || releaseYear < era) movieDbs.remove(i);
        }
        return movieDbs;
    }


}