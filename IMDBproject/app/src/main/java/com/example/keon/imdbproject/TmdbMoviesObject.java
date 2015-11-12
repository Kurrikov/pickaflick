package com.example.keon.imdbproject;

import android.content.Context;
import android.media.Image;

//import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.MovieDb;

/**
 * Created by Keon on 11/8/2015.
 */
public class TmdbMoviesObject extends TmdbObject {

    public class ImageException extends Exception {
        public ImageException() {
            super("Image does not exist");
        }
    }

    private List<MovieAttributes> moviesList;
    private List<MovieDb> movieDbs;

    public TmdbMoviesObject(String MPAA_Rating, String genre, int era, float minRating) throws GenreException {
        super(MPAA_Rating, genre, era, minRating);
        moviesList = new ArrayList<MovieAttributes>();
    }

    public void createMoviesList(){
        for (int i = 0; i < movieDbs.size(); i++) {
            this.moviesList.add(new MovieAttributes(movieDbs.get(i)));
        }
    }

    public List<MovieAttributes> getMoviesList() {
        return this.moviesList;
    }

    //the default tmdb method does a poor job in filtering movies by years of release, so this method was added to rectify that issue
    public void listCorrector() {
        for (int i = 0; i < movieDbs.size(); i++) {
            int releaseYear = Integer.parseInt(movieDbs.get(i).getReleaseDate().substring(0, 4));
            if (releaseYear > era + 9 || releaseYear < era) movieDbs.remove(i);
        }
    }


    public void setMovieDbs(List<MovieDb> movieDbs){
        this.movieDbs = movieDbs;
    }

}