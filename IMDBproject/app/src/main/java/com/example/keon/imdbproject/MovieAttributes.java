package com.example.keon.imdbproject;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import java.io.IOException;
import java.net.URL;
import info.movito.themoviedbapi.model.MovieDb;


/**
 * Created by Keon on 11/10/2015.
 * This class stores all the movie info for a given movie. It also
 * fetches the movie poster from the appropriate tmdb link.
 */
public class MovieAttributes {

    private String title;
    private String overview;
    private String imageURL;
    private int runtime;
    private float ratings;
    private String releaseDate;
    private Bitmap movieImage;

    public MovieAttributes(MovieDb movie){
        title = movie.getTitle();
        overview = movie.getOverview();
        imageURL = "http://image.tmdb.org/t/p/w500" + movie.getPosterPath();
        runtime = movie.getRuntime();
        ratings = movie.getVoteAverage();
        releaseDate = movie.getReleaseDate();
        setImage();
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

    public Bitmap getImage(){
        return movieImage;
    }

    private void setImage(){
        URL url = null;
        try {
            url = new URL(imageURL);
            movieImage = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }
        catch (IOException e) {
            //do nothing. just get a null image.
        }
    }

    public ImageView getImageView(ImageView imageView, int width, int height){
        movieImage = Bitmap.createScaledBitmap(movieImage, width, height ,false);
        imageView.setImageBitmap(movieImage);
        return imageView;
    }

    public ImageView getImageView(ImageView imageView){
        imageView.setImageBitmap(movieImage);
        return imageView;
    }

}
