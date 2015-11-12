package com.example.keon.imdbproject;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.people.Person;
import info.movito.themoviedbapi.model.people.PersonCast;

/**
 * Created by Keon on 11/10/2015.
 */
public class MovieAttributes {

    private String title;
    private String overview;
    private String imageURL;
    private int runtime;
    private float ratings;
    private String releaseDate;
    private ImageView movieImage;
    //float popularity;

    public MovieAttributes(MovieDb movie){
        this.title = movie.getTitle();
        this.overview = movie.getOverview();
        this.imageURL = "http://image.tmdb.org/t/p/" + movie.getPosterPath();
        this.runtime = movie.getRuntime();
        this.ratings = movie.getVoteAverage();
        this.releaseDate = movie.getReleaseDate();
        //this.popularity = movie.getPopularity();
        loadImage(this.imageURL);
    }

    public String getTitle(){
        return title;
    }

    public String getOverview(){
        return overview;
    }

    public String getImageURL() {
        return imageURL;
    }

    public float getRatings() {
        return ratings;
    }

    public String getReleaseDate(){
        return releaseDate;
    }

    public ImageView getImage(){
        return movieImage;
    }

    public void loadImage(String imageURL){
        /*Picasso.with(moviePoster.getContext())
                .load(imageURL)
                .resize(this.imageHeight,this.imageWidth)
                .into(movieImage);*/
    }
}
