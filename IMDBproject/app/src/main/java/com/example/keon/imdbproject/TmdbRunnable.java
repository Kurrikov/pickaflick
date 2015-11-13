package com.example.keon.imdbproject;

import java.util.List;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbDiscover;
import info.movito.themoviedbapi.model.Discover;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

/**
 * Created by Keon on 11/4/2015.
 */
public class TmdbRunnable implements Runnable{

    private TmdbMoviesObject tmdbMoviesobject;
    private String API_ID = "";

    TmdbRunnable(TmdbMoviesObject tmdbMoviesobject, String API_ID){
        this.tmdbMoviesobject = tmdbMoviesobject;
        this.API_ID = API_ID;
    }

    public void run() {
        Discover discover = tmdbMoviesobject.getDiscover();
        TmdbDiscover tmdbDiscover = new TmdbApi(API_ID).getDiscover();
        MovieResultsPage movieResultsPage = tmdbDiscover.getDiscover(discover);
        List<MovieDb> filteredMovies = tmdbMoviesobject.listCorrector(movieResultsPage.getResults());
        tmdbMoviesobject.setMoviesList(filteredMovies);
    }
}
